package dreamteam.kryptotracker.domain.user;

import dreamteam.kryptotracker.domain.wallet.WalletRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dreamteam.kryptotracker.domain.user.ResultDescription.CANNOT_CHANGE_STATUS_FROM_TERMINATED;
import static dreamteam.kryptotracker.domain.user.ResultDescription.LOGIN_SUCCESSFULLY;
import static dreamteam.kryptotracker.domain.user.ResultDescription.PASSWORD_CHANGED;
import static dreamteam.kryptotracker.domain.user.ResultDescription.STATUS_CHANGED;
import static dreamteam.kryptotracker.domain.user.ResultDescription.USER_ADDED;
import static dreamteam.kryptotracker.domain.user.ResultDescription.USER_ALREADY_EXISTS;
import static dreamteam.kryptotracker.domain.user.ResultDescription.USER_NOT_EXISTS;
import static dreamteam.kryptotracker.domain.user.ResultDescription.WRONG_LOGIN_OR_PASSWORD;
import static dreamteam.kryptotracker.domain.user.ResultDescription.WRONG_STATUS;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.walletRepository = walletRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).block();
    }

    public Mono<User> findBy(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Mono<RegistrationResult> registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, UserRole.USER);

        return userRepository.findByUsername(username)
                .map(usr -> new RegistrationResult(false, String.format(USER_ALREADY_EXISTS.getDescription(), username)))
                .switchIfEmpty(addUser(user));
    }

    public Mono<RegistrationResult> registerAdmin(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, UserRole.ADMIN);

        return userRepository.findByUsername(username)
                .map(appuser -> new RegistrationResult(false, String.format(USER_ALREADY_EXISTS.getDescription(), username)))
                .switchIfEmpty(addAdmin(user));
    }

    private Mono<RegistrationResult> addUser(User user) {
        return userRepository.add(user)
                .zipWith(walletRepository.createFor(user.getUsername()), (u, w) -> new RegistrationResult(true, USER_ADDED.getDescription()));
    }

    private Mono<RegistrationResult> addAdmin(User user) {
        return userRepository.add(user)
                .map(usr -> new RegistrationResult(true, USER_ADDED.getDescription()));
    }

    public Mono<LoginResult> loginUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword())
                        ? new LoginResult(true, String.format(LOGIN_SUCCESSFULLY.getDescription(), username))
                        : new LoginResult(false, WRONG_LOGIN_OR_PASSWORD.getDescription()))
                .switchIfEmpty(Mono.just(new LoginResult(false, String.format(USER_NOT_EXISTS.getDescription(), username))));
    }

    public Mono<UpdateResult> updateState(String username, UserState userState) {
        return userRepository.findByUsername(username)
                .flatMap(user -> updateState(user, userState))
                .switchIfEmpty(Mono.just(new UpdateResult(false, USER_ADDED.getDescription())));
    }

    private Mono<UpdateResult> updateState(User user, UserState userState) {
        if (userState == null) {
            return Mono.just(new UpdateResult(false, WRONG_STATUS.getDescription()));
        }
        if (UserState.TERMINATED == user.getUserState()) {
            return Mono.just(new UpdateResult(false, CANNOT_CHANGE_STATUS_FROM_TERMINATED.getDescription()));
        }
        return userRepository.setUserState(user, userState)
                .map(usr -> new UpdateResult(true, STATUS_CHANGED.getDescription()))
                .switchIfEmpty(Mono.just(new UpdateResult(false, String.format(USER_NOT_EXISTS.getDescription(), user.getUsername()))));
    }

    public Mono<UpdateResult> updatePassword(String username, String password) {
        return userRepository.findByUsername(username)
                .flatMap(usr -> updatePassword(usr, password))
                .switchIfEmpty(Mono.just(new UpdateResult(false, String.format(USER_NOT_EXISTS.getDescription(), username))));
    }

    private Mono<UpdateResult> updatePassword(User user, String password) {
        return userRepository.setPassword(user, passwordEncoder.encode(password))
                .map(usr -> new UpdateResult(true, PASSWORD_CHANGED.getDescription()))
                .switchIfEmpty(Mono.just(new UpdateResult(false, String.format(USER_NOT_EXISTS.getDescription(), user.getUsername()))));
    }
}
