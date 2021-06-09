package dreamteam.kryptotracker.infrastructure.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface MongoUserRepository extends ReactiveMongoRepository<MongoUser, String> {
    Mono<MongoUser> findByUsername(String username);
}
