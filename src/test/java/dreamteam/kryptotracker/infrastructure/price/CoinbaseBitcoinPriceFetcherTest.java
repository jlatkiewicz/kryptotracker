package dreamteam.kryptotracker.infrastructure.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import dreamteam.kryptotracker.infrastructure.price.CoinbaseBitcoinPriceFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CoinbaseBitcoinPriceFetcherTest {
    @Value("localhost:8089")
    private String base;
    @Value("/v2/prices/spot")
    private String uri;
    @Autowired
    private CoinbaseBitcoinPriceFetcher priceFetcher;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void addressIsConfiguredProperly() {
        assertEquals(base, priceFetcher.coinbaseConfiguration.getBase());
        assertEquals(uri, priceFetcher.coinbaseConfiguration.getUri());
    }
}