package dreamteam.kryptotracker.domain.user;

public class UpdateResult {

    private final boolean successful;
    private final String description;

    public UpdateResult(boolean successful, String description) {
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
