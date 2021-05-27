package dreamteam.kryptotracker.domain.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUser implements UserDetails {
    private final String username;
    private final String password;
    private final AppUserRole appUserRole;
    private final Boolean locked;
    private final Boolean enabled;

    public AppUser(String username,
                   String password,
                   AppUserRole appUserRole) {
        this.username = username;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = false;
        this.enabled = true;
    }

    private AppUser(String username,
                    String password,
                    AppUserRole appUserRole,
                    boolean locked,
                    boolean enabled) {
        this.username = username;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
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
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }
}
