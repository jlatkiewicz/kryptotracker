package dreamteam.kryptotracker.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CoindeskBitcoinPriceFetcherTest {
    @Value("localhost:8089")
    private String base;
    @Value("/v1/bpi/currentprice/PLN.json")
    private String uri;
    @Autowired
    private CoindeskBitcoinPriceFetcher priceFetcher;


    @Test
    public void addressIsConfiguredProperly() {
        assertEquals(base, priceFetcher.coindeskConfiguration.getBase());
        assertEquals(uri, priceFetcher.coindeskConfiguration.getUri());
    }

}