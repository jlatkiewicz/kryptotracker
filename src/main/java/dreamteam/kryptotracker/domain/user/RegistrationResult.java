package dreamteam.kryptotracker.domain.user;

public class RegistrationResult {
    private final boolean successful;

    public RegistrationResult(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
