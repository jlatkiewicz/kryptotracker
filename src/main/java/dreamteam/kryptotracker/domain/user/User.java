package dreamteam.kryptotracker.domain.user;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private final String username;
    private final String password;
    private final UserRole userRole;
    private final UserState userState;

    public User(String username,
                String password,
                UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userState = UserState.ACTIVE;
    }

    public User(String username,
                String password,
                UserRole userRole,
                UserState userState) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userState = userState;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userState == UserState.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userState == UserState.ACTIVE;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserState getUserState() {
        return userState;
    }

    public User withUserState(UserState state) {
        return new User(username, password, userRole, state);
    }

    public User withPassword(String password) {
        return new User(username, password, userRole, userState);
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }
}
