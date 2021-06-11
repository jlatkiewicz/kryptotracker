package dreamteam.kryptotracker.domain.user;

public enum UserState {
    ACTIVE,
    LOCKED,
    TERMINATED;

    public static UserState from(String state) {
        return switch (state) {
            case "ACTIVE", "active" -> ACTIVE;
            case "LOCKED", "locked" -> LOCKED;
            case "TERMINATED", "terminated" -> TERMINATED;
            default -> null;
        };
    }
}
