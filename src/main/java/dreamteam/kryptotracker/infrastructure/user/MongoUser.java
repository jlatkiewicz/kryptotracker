package dreamteam.kryptotracker.infrastructure.user;

import dreamteam.kryptotracker.domain.user.User;
import dreamteam.kryptotracker.domain.user.UserRole;
import dreamteam.kryptotracker.domain.user.UserState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class MongoUser {
    @Id
    private final String username;
    private final String password;
    private final String userRole;
    private final String state;

    public MongoUser(String username, String password, String userRole, String state) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getState() {
        return state;
    }

    public User toUser() {
        return new User(
                username,
                password,
                UserRole.valueOf(userRole),
                UserState.from(state)
        );
    }

    public static MongoUser fromUser(User user) {
        return new MongoUser(
                user.getUsername(),
                user.getPassword(),
                user.getUserRole().name(),
                user.getUserState().name()
        );
    }
}
