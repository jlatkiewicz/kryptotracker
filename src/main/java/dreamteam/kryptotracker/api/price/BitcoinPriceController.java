package dreamteam.kryptotracker.api.price;

import dreamteam.kryptotracker.domain.price.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BitcoinPriceController {
    private final PriceService priceService;

    public BitcoinPriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price")
    public Mono<BitcoinPriceResponse> bitcoinPrice() {
        return priceService.getBitcoinPrice()
                .map(BitcoinPriceResponse::new);
    }

}
