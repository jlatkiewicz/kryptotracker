package dreamteam.kryptotracker.domain.wallet;

import java.math.BigDecimal;

public class Wallet {
    private final BigDecimal bitcoinAmount;

    public Wallet(BigDecimal bitcoinAmount) {
        this.bitcoinAmount = bitcoinAmount;
    }

    public Wallet add(BigDecimal bitcoinAmount) {
        return new Wallet(this.bitcoinAmount.add(bitcoinAmount));
    }

    public Wallet subtract(BigDecimal bitcoinAmount) {
        return new Wallet(this.bitcoinAmount.subtract(bitcoinAmount));
    }

    public BigDecimal getBitcoinAmount() {
        return bitcoinAmount;
    }
}
