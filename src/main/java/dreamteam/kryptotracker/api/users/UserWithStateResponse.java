package dreamteam.kryptotracker.api.users;

import dreamteam.kryptotracker.domain.user.User;

public class UserWithStateResponse {
    private final String username;
    private final String state;

    private UserWithStateResponse(String username, String userState) {
        this.username = username;
        this.state = userState;
    }

    public static UserWithStateResponse from(User user) {
        return new UserWithStateResponse(
                user.getUsername(),
                user.getUserState().name()
        );
    }

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }

}
