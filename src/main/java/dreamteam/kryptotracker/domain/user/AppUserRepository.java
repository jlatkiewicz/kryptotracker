package dreamteam.kryptotracker.domain.user;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository {

    Optional<AppUser> findByUsername(String username);

    void add(AppUser user);
}
