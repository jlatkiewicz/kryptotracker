package dreamteam.kryptotracker.domain.user;

public class LoginResult {

    private final boolean successful;

    public LoginResult(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
