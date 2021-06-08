package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.security.auth.login.LoginException;

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
        return userService.signUpUser(request.getUsername(), request.getPassword())
                .map(result -> {
                    if (result.isSuccessful()) return request.getUsername() + " registered successfully";
                    else return request.getUsername() + " already exists";
                });
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @PostMapping("/users/login")
    public Mono<String> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getUsername(), request.getPassword())
                .flatMap(result -> {
                    if (result.isSuccessful()) return Mono.just(request.getUsername() + " login successfully");
                    else return Mono.error(new LoginException("Wrong login or password"));
                });
    }

    @CrossOrigin(origins = "http://localhost:8088")
    @GetMapping("/users/{username}")
    public Mono<UserResponse> get(@PathVariable("username") String username) {
        return userService.findBy(username)
                .zipWith(walletService.findBy(username), UserResponse::from)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(username)));
    }

}
