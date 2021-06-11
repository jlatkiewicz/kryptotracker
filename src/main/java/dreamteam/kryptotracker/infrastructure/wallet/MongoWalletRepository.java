package dreamteam.kryptotracker.infrastructure.wallet;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoWalletRepository extends ReactiveMongoRepository<MongoWallet, String> {
}
