package dreamteam.kryptotracker.infrastructure;

import dreamteam.kryptotracker.domain.BitcoinPriceFetcher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class StubBitcoinPriceFetcher implements BitcoinPriceFetcher {
    @Override
    public Mono<BigDecimal> fetchBitcoinPrice() {
        return Mono.just(BigDecimal.valueOf(10000));
    }
}
