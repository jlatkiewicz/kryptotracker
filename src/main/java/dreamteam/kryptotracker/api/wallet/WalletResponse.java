package dreamteam.kryptotracker.api.wallet;

import dreamteam.kryptotracker.domain.wallet.Wallet;
import java.util.Optional;

public class WalletResponse {
    private final String userId;
    private final String bitcoinAmount;

    private WalletResponse(String userId, String bitcoinAmount) {
        this.userId = userId;
        this.bitcoinAmount = bitcoinAmount;
    }

    public static WalletResponse from(Wallet wallet, String userId) {
        return Optional.ofNullable(wallet)
                .map(w -> new WalletResponse(userId, w.getBitcoinAmount().toString()))
                .orElse(null);
    }

    public String getUserId() {
        return userId;
    }

    public String getBitcoinAmount() {
        return bitcoinAmount;
    }
}
