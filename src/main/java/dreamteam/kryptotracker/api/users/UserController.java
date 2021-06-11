package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.user.UserState;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import javax.security.auth.login.LoginException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dreamteam.kryptotracker.domain.user.ResultDescription.WRONG_LOGIN_OR_PASSWORD;

@RestController
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/users/register")
    public Mono<String> register(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/users/registerAdmin")
    public Mono<String> registerAdmin(@RequestBody RegistrationRequest request) {
        return userService.registerAdmin(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/users/login")
    public Mono<LoginResponse> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getUsername(), request.getPassword())
                .map(LoginResponse::from)
                .switchIfEmpty(Mono.error(new LoginException(WRONG_LOGIN_OR_PASSWORD.getDescription())));
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @GetMapping("/users/{username}")
    public Mono<UserResponse> get(@PathVariable("username") String username) {
        return userService.findBy(username)
                .zipWith(walletService.findBy(username), UserResponse::from)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(username)));
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @GetMapping("/users")
    public Flux<UserWithStateResponse> getAllUsersUsernamesWithState() {
        return userService.getAllUsers()
                .filter(user -> !user.isAdmin())
                .map(UserWithStateResponse::from);
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PutMapping("/users/{username}")
    public Mono<String> update(@PathVariable("username") String username, @RequestParam("state") String state) {
        return userService.updateState(username, UserState.from(state))
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new IllegalArgumentException(result.getDescription()));
                });
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PutMapping("/users/{username}/password")
    public Mono<String> updatePassword(@PathVariable("username") String username, @RequestParam("password") String password) {
        return userService.updatePassword(username, password)
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new IllegalArgumentException(result.getDescription()));
                });
    }

}
