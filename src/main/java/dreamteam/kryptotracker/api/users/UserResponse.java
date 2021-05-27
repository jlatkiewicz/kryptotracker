package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.api.wallet.WalletResponse;
import dreamteam.kryptotracker.domain.user.AppUser;
import dreamteam.kryptotracker.domain.user.AppUserRole;
import dreamteam.kryptotracker.domain.wallet.Wallet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserResponse {
    private final String username;
    private final Boolean nonLocked;
    private final Boolean enabled;
    private final WalletResponse wallet;

    private UserResponse(String username, Boolean nonLocked, Boolean enabled, WalletResponse wallet) {
        this.username = username;
        this.nonLocked = nonLocked;
        this.enabled = enabled;
        this.wallet = wallet;
    }

    public static UserResponse from(AppUser appUser, Wallet wallet) {
        return new UserResponse(
                appUser.getUsername(),
                appUser.isAccountNonLocked(),
                appUser.isEnabled(),
                WalletResponse.from(wallet, appUser.getUsername())
        );
    }

    public String getUsername() {
        return username;
    }

    public Boolean getNonLocked() {
        return nonLocked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public WalletResponse getWallet() {
        return wallet;
    }
}
