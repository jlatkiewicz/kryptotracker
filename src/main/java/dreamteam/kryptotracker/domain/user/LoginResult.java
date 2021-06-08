package dreamteam.kryptotracker.domain.user;

public class LoginResult {

    private final boolean successful;
    private final String description;

    public LoginResult(boolean successful, String description) {
        this.successful = successful;
        this.description = description;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getDescription() {
        return description;
    }
}
