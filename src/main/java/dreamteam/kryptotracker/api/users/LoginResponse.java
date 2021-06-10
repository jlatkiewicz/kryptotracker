package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.User;

public class LoginResponse {

    private final String username;
    private final boolean isAdmin;

    public LoginResponse(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getUsername(),
                user.isAdmin()
        );
    }
}
