package dreamteam.kryptotracker.domain.user;

public interface ResultDescription {

    String CANNOT_CHANGE_STATUS_FROM_TERMINATED = "Cannot change status from TERMINATED";
    String LOGIN_SUCCESSFULLY = "User '%s' login successfully";
    String STATUS_CHANGED = "Status changed";
    String USER_ADDED = "User added";
    String USER_ALREADY_EXISTS = "User '%s' already exists";
    String USER_NOT_EXISTS = "User '%s' doesn't exists";
    String WRONG_LOGIN_OR_PASSWORD = "Wrong login or password";
    String WRONG_STATUS = "Wrong status";
}
