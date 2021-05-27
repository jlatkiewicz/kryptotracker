package dreamteam.kryptotracker.api.wallet;

import dreamteam.kryptotracker.domain.wallet.Wallet;

public class WalletResponse {
    private final String userId;
    private final String bitcoinAmount;

    private WalletResponse(String userId, String bitcoinAmount) {
        this.userId = userId;
        this.bitcoinAmount = bitcoinAmount;
    }

    public static WalletResponse from(Wallet wallet, String userId) {
        return new WalletResponse(userId, wallet.getBitcoinAmount().toString());
    }

    public String getUserId() {
        return userId;
    }

    public String getBitcoinAmount() {
        return bitcoinAmount;
    }
}
