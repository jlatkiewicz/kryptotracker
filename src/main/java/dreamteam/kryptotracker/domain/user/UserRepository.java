package dreamteam.kryptotracker.domain.user;

import java.util.Set;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository {

    Mono<Set<String>> getAllUsernames();
    Mono<User> add(User user);
    Mono<User> findByUsername(String username);
    Mono<User> setUserState(User user, UserState userState);
    Mono<User> setPassword(User user, String password);
}
