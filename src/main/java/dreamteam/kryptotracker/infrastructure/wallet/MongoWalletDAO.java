package dreamteam.kryptotracker.infrastructure.wallet;

import dreamteam.kryptotracker.domain.wallet.Wallet;
import dreamteam.kryptotracker.domain.wallet.WalletRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Primary
@Repository
public class MongoWalletDAO implements WalletRepository {
    private final MongoWalletRepository walletRepository;

    public MongoWalletDAO(MongoWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Mono<Wallet> findBy(String userId) {
        return walletRepository.findById(userId).map(MongoWallet::toWallet);
    }

    @Override
    public Mono<Wallet> overwrite(String userId, Wallet wallet) {
        return walletRepository.save(MongoWallet.from(userId, wallet)).map(MongoWallet::toWallet);
    }

    @Override
    public Mono<Wallet> createFor(String userId) {
        MongoWallet newWallet = new MongoWallet(userId, BigDecimal.ZERO);
        return walletRepository.save(newWallet).map(MongoWallet::toWallet);
    }
}
