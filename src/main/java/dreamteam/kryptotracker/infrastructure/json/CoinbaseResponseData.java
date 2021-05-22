package dreamteam.kryptotracker.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinbaseResponseData {
    private String base;
    private String currency;
    private String amount;

    @JsonCreator
    public CoinbaseResponseData(@JsonProperty("base") String base,
                                @JsonProperty("currency") String currency,
                                @JsonProperty("amount") String amount) {
        this.base = base;
        this.currency = currency;
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
