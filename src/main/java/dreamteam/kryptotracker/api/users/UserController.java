package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.User;
import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.user.UserState;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import javax.security.auth.login.LoginException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/users/register")
    public Mono<String> register(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @PostMapping("/users/registerAdmin")
    public Mono<String> registerAdmin(@RequestBody RegistrationRequest request) {
        return userService.registerAdmin(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new LoginException(result.getDescription()));
                });
    }

    @PostMapping("/users/login")
    public Mono<LoginResponse> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getUsername(), request.getPassword())
                .map(LoginResponse::from)
                .switchIfEmpty(Mono.error(new LoginException(WRONG_LOGIN_OR_PASSWORD.getDescription())));
    }

    @GetMapping("/users/{username}")
    public Mono<UserResponse> get(@PathVariable("username") String username) {
        return userService.findBy(username)
                .map(usr -> UserResponse.from(usr, walletService.findBy(username).block()))
                .switchIfEmpty(Mono.error(new LoginException(username)));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<UserWithStateResponse> getAllUsersUsernamesWithState() {
        return userService.getAllUsers()
                .filter(user -> !user.isAdmin())
                .map(UserWithStateResponse::from);
    }

    @PutMapping("/users/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> update(@PathVariable("username") String username, @RequestParam("state") String state) {
        return userService.updateState(username, UserState.from(state))
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(result.getDescription());
                    else return Mono.error(new IllegalArgumentException(result.getDescription()));
                });
    }

    @PutMapping("/users/{username}/password")
    public Mono<String> updatePassword(@PathVariable("username") String username, @RequestParam("password") String password) {
        User currentlyLoginUser = getCurrentlyLoginUser();
        if (currentlyLoginUser.getUsername().equals(username) || currentlyLoginUser.isAdmin()) {
            return userService.updatePassword(username, password)
                    .flatMap(result -> {
                        if (result.isSuccessful()) return Mono.just(result.getDescription());
                        else return Mono.error(new IllegalArgumentException(result.getDescription()));
                    });
        } else
            return Mono.error(new LoginException(
                    String.format("'%s' cannot change password of user '%s'", currentlyLoginUser.getUsername(), username)));
    }

    private User getCurrentlyLoginUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
