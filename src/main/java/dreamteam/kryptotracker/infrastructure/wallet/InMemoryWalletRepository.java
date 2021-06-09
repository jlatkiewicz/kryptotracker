package dreamteam.kryptotracker.infrastructure.wallet;

import dreamteam.kryptotracker.domain.wallet.Wallet;
import dreamteam.kryptotracker.domain.wallet.WalletRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryWalletRepository implements WalletRepository {
    private final Map<String, Wallet> wallets = new HashMap<>();

    @Override
    public Mono<Wallet> findBy(String userId) {
        Optional<Wallet> maybeWallet = Optional.ofNullable(wallets.get(userId));
        return Mono.justOrEmpty(maybeWallet);
    }

    @Override
    public Mono<Wallet> overwrite(String userId, Wallet wallet) {
        Optional.ofNullable(wallets.get(userId))
                .ifPresent(q -> wallets.put(userId, wallet));
        return Mono.justOrEmpty(Optional.ofNullable(wallets.get(userId)));
    }

    @Override
    public Mono<Wallet> createFor(String userId) {
        wallets.put(userId, new Wallet(BigDecimal.ZERO));
        return Mono.justOrEmpty(Optional.ofNullable(wallets.get(userId)));
    }
}
