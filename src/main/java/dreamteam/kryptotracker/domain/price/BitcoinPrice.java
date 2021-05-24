package dreamteam.kryptotracker.domain.price;

import java.math.BigDecimal;

public class BitcoinPrice {
    public final BigDecimal price;

    private BitcoinPrice(BigDecimal price) {
        this.price = price;
    }
    static BitcoinPrice from(BigDecimal price) {
        return new BitcoinPrice(price);
    }
}
