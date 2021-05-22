package dreamteam.kryptotracker.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoinbaseResponse {
    private CoinbaseResponseData data;

    @JsonCreator
    public CoinbaseResponse(@JsonProperty("data") CoinbaseResponseData data) {
        this.data = data;
    }

    public CoinbaseResponseData getData() {
        return data;
    }

    public void setData(CoinbaseResponseData data) {
        this.data = data;
    }

    public BigDecimal getBitcoinPrice() {
        return new BigDecimal(data.getAmount());
    }
}
