package dreamteam.kryptotracker.infrastructure.price;

import dreamteam.kryptotracker.config.CoinbaseConfiguration;
import dreamteam.kryptotracker.domain.price.BitcoinPriceFetcher;
import dreamteam.kryptotracker.infrastructure.price.json.CoinbaseResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class CoinbaseBitcoinPriceFetcher implements BitcoinPriceFetcher {
    final CoinbaseConfiguration coinbaseConfiguration;
    final WebClient webClient;


    public CoinbaseBitcoinPriceFetcher(CoinbaseConfiguration coinbaseConfiguration, WebClient webClient) {
        this.coinbaseConfiguration = coinbaseConfiguration;
        this.webClient = webClient;
    }


    @Override
    public Mono<BigDecimal> fetchBitcoinPrice() {
        return webClient.get()
                .uri(this::buildUri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CoinbaseResponse.class)
                .map(CoinbaseResponse::getBitcoinPrice);
    }

    private URI buildUri(UriBuilder uriBuilder) {
        return uriBuilder
                .path(coinbaseConfiguration.getUri())
                .queryParam("currency", "PLN")
                .build();
    }
}
