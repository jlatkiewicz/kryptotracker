package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.api.wallet.WalletResponse;
import dreamteam.kryptotracker.domain.user.User;
import dreamteam.kryptotracker.domain.wallet.Wallet;
import java.util.Optional;

public class UserResponse {
    private final String username;
    private final String userRole;
    private final String userState;
    private final WalletResponse wallet;

    private UserResponse(String username, String userRole, String userState, WalletResponse wallet) {
        this.username = username;
        this.userRole = userRole;
        this.userState = userState;
        this.wallet = wallet;
    }

    public static UserResponse from(User user, Wallet wallet) {
        return new UserResponse(
                user.getUsername(),
                user.getUserRole().name(),
                user.getUserState().name(),
                Optional.ofNullable(wallet)
                        .map(w -> WalletResponse.from(w, user.getUsername()))
                        .orElse(null)
        );
    }

    public String getUsername() {
        return username;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getUserState() {
        return userState;
    }

    public WalletResponse getWallet() {
        return wallet;
    }
}
