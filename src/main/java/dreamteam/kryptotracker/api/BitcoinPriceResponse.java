package dreamteam.kryptotracker.api;

import dreamteam.kryptotracker.domain.BitcoinPrice;

import java.math.BigDecimal;

public class BitcoinPriceResponse {
    public final BigDecimal bitcoinPriceInPln;

    public BitcoinPriceResponse(BitcoinPrice bitcoinPrice) {
        this.bitcoinPriceInPln = bitcoinPrice.price;
    }

}
