package dreamteam.kryptotracker.domain.user;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AppUserRepository {

    Mono<AppUser> findByUsername(String username);

    Mono<AppUser> add(AppUser user);
}
