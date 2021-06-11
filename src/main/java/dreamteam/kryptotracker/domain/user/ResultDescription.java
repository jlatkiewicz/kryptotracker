package dreamteam.kryptotracker.domain.user;

public enum ResultDescription {

    CANNOT_CHANGE_STATUS_FROM_TERMINATED("Cannot change status from TERMINATED"),
    PASSWORD_CHANGED("Password changed"),
    STATUS_CHANGED("Status changed"),
    USER_ADDED("User added"),
    USER_ALREADY_EXISTS("User '%s' already exists"),
    USER_NOT_EXISTS("User '%s' doesn't exists"),
    WRONG_LOGIN_OR_PASSWORD("Wrong login or password"),
    WRONG_STATUS("Wrong status");

    private final String description;

    ResultDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
