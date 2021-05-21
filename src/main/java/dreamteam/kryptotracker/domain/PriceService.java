package dreamteam.kryptotracker.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PriceService {
    private final BitcoinPriceFetcher priceFetcher;

    public PriceService(BitcoinPriceFetcher priceFetcher) {
        this.priceFetcher = priceFetcher;
    }

    public Mono<BitcoinPrice> getBitcoinPrice() {
        return priceFetcher.fetchBitcoinPrice()
                .map(BitcoinPrice::from);
    }
}
