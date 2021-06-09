package dreamteam.kryptotracker.domain.user;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository {

    Flux<String> getAllUsernames();
    Mono<User> add(User user);
    Mono<User> findByUsername(String username);
    Mono<User> setUserState(User user, UserState userState);
}
