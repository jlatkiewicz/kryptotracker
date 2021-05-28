package dreamteam.kryptotracker.infrastructure.user;

import dreamteam.kryptotracker.domain.user.AppUser;
import dreamteam.kryptotracker.domain.user.AppUserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class InMemoryUserRepository implements AppUserRepository {

    private final Map<String, AppUser> content = new HashMap<>();

    @Override
    public Mono<AppUser> findByUsername(String username) {
        Optional<AppUser> maybeUser = Optional.ofNullable(content.get(username));
        return Mono.justOrEmpty(maybeUser);
    }

    @Override
    public Mono<AppUser> add(AppUser user) {
        content.put(user.getUsername(), user);
        return Mono.justOrEmpty(Optional.ofNullable(content.get(user.getUsername())));
    }

}
