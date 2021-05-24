package dreamteam.kryptotracker.infrastructure.user;

import dreamteam.kryptotracker.domain.user.AppUser;
import dreamteam.kryptotracker.domain.user.AppUserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserRepository implements AppUserRepository {

    private final Map<String, AppUser> content = new HashMap<>();

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return Optional.ofNullable(content.get(username));
    }

    @Override
    public void add(AppUser user) {
        content.put(user.getUsername(), user);
    }

}
