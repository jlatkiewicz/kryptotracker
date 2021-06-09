package dreamteam.kryptotracker.infrastructure.user;

import dreamteam.kryptotracker.domain.user.User;
import dreamteam.kryptotracker.domain.user.UserRepository;
import dreamteam.kryptotracker.domain.user.UserState;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Repository
public class MongoUserDAO implements UserRepository {
    private final MongoUserRepository mongoUserRepository;

    public MongoUserDAO(MongoUserRepository mongoUserRepository) {
        this.mongoUserRepository = mongoUserRepository;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return mongoUserRepository.findByUsername(username)
                .map(MongoUser::toUser);
    }

    @Override
    public Mono<User> setUserState(User user, UserState userState) {
        MongoUser userWithState = MongoUser.fromUser(user.withUserState(userState));
        return mongoUserRepository.save(userWithState).map(MongoUser::toUser);
    }

    @Override
    public Flux<String> getAllUsernames() {
        return mongoUserRepository.findAll()
                .map(MongoUser::getUsername);
    }

    @Override
    public Mono<User> add(User user) {
        return mongoUserRepository.save(MongoUser.fromUser(user))
                .map(MongoUser::toUser);
    }
}
