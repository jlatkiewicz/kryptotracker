package dreamteam.kryptotracker.domain.user;

import dreamteam.kryptotracker.domain.wallet.WalletRepository;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dreamteam.kryptotracker.domain.user.ResultDescription.CANNOT_CHANGE_STATUS_FROM_TERMINATED;
import static dreamteam.kryptotracker.domain.user.ResultDescription.LOGIN_SUCCESSFULLY;
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

    public Flux<String> getAllUsernames() {
        return userRepository.getAllUsernames();
    }

    public Mono<RegistrationResult> signUpUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, UserRole.USER);

        return userRepository.findByUsername(username)
                .map(usr -> new RegistrationResult(false, String.format(USER_ALREADY_EXISTS.getDescription(), username)))
                .switchIfEmpty(addUser(user));
    }

    private Mono<RegistrationResult> addUser(User user) {
        return userRepository.add(user)
                .zipWith(walletRepository.createFor(user.getUsername()), (u, w) -> new RegistrationResult(true, USER_ADDED.getDescription()));
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

}
