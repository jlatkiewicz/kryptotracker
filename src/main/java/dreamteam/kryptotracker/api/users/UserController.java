package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.UserService;
import dreamteam.kryptotracker.domain.wallet.WalletService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
                .map(result -> {
                    if (result.isSuccessful()) return request.getUsername() + " registered successfully";
                    else return request.getUsername() + " already exists";
                });
    }

    @PostMapping("/users/login")
    public Mono<String> login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getUsername(), request.getPassword())
                .map(result -> {
                    if (result.isSuccessful()) return request.getUsername() + " login successfully";
                    else return "Wrong login or password";
                });
    }

    @GetMapping("/users/{username}")
    public Mono<UserResponse> get(@PathVariable("username") String username) {
        return userService.findBy(username)
                .zipWith(walletService.findBy(username), UserResponse::from)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(username)));
    }

}
