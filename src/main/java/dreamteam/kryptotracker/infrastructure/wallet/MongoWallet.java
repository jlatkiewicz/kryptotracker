package dreamteam.kryptotracker.infrastructure.wallet;

import dreamteam.kryptotracker.domain.wallet.Wallet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "wallets")
public class MongoWallet {
    @Id
    private final String username;
    private final BigDecimal amount;

    public MongoWallet(String username, BigDecimal amount) {
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet toWallet() {
        return new Wallet(amount);
    }

    public static MongoWallet from(String username, Wallet wallet) {
        return new MongoWallet(username, wallet.getBitcoinAmount());
    }
}
