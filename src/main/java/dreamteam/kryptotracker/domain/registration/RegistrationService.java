package dreamteam.kryptotracker.domain.registration;

import dreamteam.kryptotracker.api.registration.RegistrationRequest;
import dreamteam.kryptotracker.domain.user.AppUser;
import dreamteam.kryptotracker.domain.user.AppUserRole;
import dreamteam.kryptotracker.domain.user.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AppUserService appUserService;

    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request) {
        appUserService.signUpUser(new AppUser(request.getUsername(), request.getPassword(), AppUserRole.USER));
        return "Registered correctly";
    }

}
