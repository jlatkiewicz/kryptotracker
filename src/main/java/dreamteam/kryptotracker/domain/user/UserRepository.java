package dreamteam.kryptotracker.domain.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository {

    Flux<User> getAllUsers();
    Mono<User> add(User user);
    Mono<User> findByUsername(String username);
    Mono<User> setUserState(User user, UserState userState);
    Mono<User> setPassword(User user, String password);
}
