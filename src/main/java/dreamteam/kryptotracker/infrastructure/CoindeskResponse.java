package dreamteam.kryptotracker.infrastructure;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoindeskResponse {
    private Map<String, BPI> bpi;


    @JsonCreator
    public CoindeskResponse(@JsonProperty("bpi") Map<String, BPI> bpi) {
        this.bpi = bpi;
    }


    Optional<BigDecimal> getBitcoinPrice() {
        return Optional.of(BigDecimal.ONE);
//        return Optional.ofNullable(bpi.get("PLN"))
//                .map(bpi -> new BigDecimal(bpi.getRate()));
    }

    public Map<String, BPI> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BPI> bpi) {
        this.bpi = bpi;
    }
}


