package dreamteam.kryptotracker.domain.wallet;


import reactor.core.publisher.Mono;

public interface WalletRepository {
    Mono<Wallet> findBy(String userId);
    Mono<Wallet> overwrite(String userId, Wallet wallet);
    Mono<Wallet> createFor(String userId);
}
