package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.user.UserState;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import java.util.Set;
import javax.security.auth.login.LoginException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @PostMapping("/users/register")
    public Mono<String> register(@RequestBody RegistrationRequest request) {
        return userService.signUpUser(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @PostMapping("/users/login")
    public Mono<String> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @GetMapping("/users/{username}")
    public Mono<UserResponse> get(@PathVariable("username") String username) {
        return userService.findBy(username)
                .zipWith(walletService.findBy(username), UserResponse::from)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(username)));
    }

    @GetMapping("/users")
    public Mono<Set<String>> getAllUsernames() {
        return userService.getAllUsernames();
    }

    @PutMapping("/users/{username}")
    public Mono<String> update(@PathVariable("username") String username, @RequestParam("state") String state) {
        return userService.updateState(username, UserState.from(state))
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new IllegalArgumentException(result.getDescription()));
                });
    }

}
