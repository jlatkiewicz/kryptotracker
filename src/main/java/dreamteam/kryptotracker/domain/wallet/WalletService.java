package dreamteam.kryptotracker.domain.wallet;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Mono<Wallet> findBy(String userId) {
        return walletRepository.findBy(userId);
    }

    public Mono<Wallet> addBitcoin(String userId, BigDecimal amount) {
        return walletRepository.findBy(userId)
                .map(wallet -> wallet.add(amount))
                .doOnNext(wallet -> walletRepository.overwrite(userId, wallet));
    }

    public Mono<Wallet> subtractBitcoin(String userId, BigDecimal amount) {
        return walletRepository.findBy(userId)
                .map(wallet -> wallet.subtract(amount))
                .doOnNext(wallet -> walletRepository.overwrite(userId, wallet));
    }

    public Mono<Wallet> setBitcoin(String userId, BigDecimal amount) {
        return walletRepository.overwrite(userId, new Wallet(amount));
    }
}
