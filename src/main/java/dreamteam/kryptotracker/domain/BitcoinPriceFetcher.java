package dreamteam.kryptotracker.domain;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface BitcoinPriceFetcher {
    Mono<BigDecimal> fetchBitcoinPrice();
}
