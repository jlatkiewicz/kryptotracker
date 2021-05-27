package dreamteam.kryptotracker.domain.user;

import dreamteam.kryptotracker.domain.wallet.WalletRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    public UserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, WalletRepository walletRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.walletRepository = walletRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).block();
    }

    public Mono<AppUser> findBy(String username) {
        return appUserRepository.findByUsername(username);
    }

    public Mono<RegistrationResult> signUpUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        AppUser appUser = new AppUser(username, encodedPassword, AppUserRole.USER);

        return appUserRepository.findByUsername(username)
                .map(appuser -> new RegistrationResult(false))
                .switchIfEmpty(addUser(appUser));

    }

    private Mono<RegistrationResult> addUser(AppUser appUser) {
        return appUserRepository.add(appUser)
                .zipWith(walletRepository.createFor(appUser.getUsername()), (u, w) -> new RegistrationResult(true));
    }

}
