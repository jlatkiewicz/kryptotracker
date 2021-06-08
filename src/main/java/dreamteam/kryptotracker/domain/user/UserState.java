package dreamteam.kryptotracker.domain.user;

public enum UserState {
    ACTIVE,
    LOCKED,
    TERMINATED;

    public static UserState from(String state) {
        if(state.equalsIgnoreCase("ACTIVE")){
            return ACTIVE;
        }
        if(state.equalsIgnoreCase("LOCKED")){
            return LOCKED;
        }
        if(state.equalsIgnoreCase("TERMINATED")){
            return TERMINATED;
        }
        return null;
    }
}
