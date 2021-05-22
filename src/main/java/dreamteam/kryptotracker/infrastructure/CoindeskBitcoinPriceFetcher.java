package dreamteam.kryptotracker.infrastructure;

import dreamteam.kryptotracker.config.CoindeskConfiguration;
import dreamteam.kryptotracker.domain.BitcoinPriceFetcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.function.Function;

@Component
public class CoindeskBitcoinPriceFetcher implements BitcoinPriceFetcher {
    final CoindeskConfiguration coindeskConfiguration;
    final WebClient webClient;


    public CoindeskBitcoinPriceFetcher(CoindeskConfiguration coindeskConfiguration, WebClient webClient) {
        this.coindeskConfiguration = coindeskConfiguration;
        this.webClient = webClient;
    }


    @Override
    public Mono<BigDecimal> fetchBitcoinPrice() {
        return webClient.get()
                .uri(coindeskConfiguration.getUri())
                .retrieve()
                .bodyToMono(CoindeskResponse.class)
                .flatMap(response -> Mono.justOrEmpty(response.getBitcoinPrice()));
    }
}
