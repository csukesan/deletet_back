package com.deletet.deletet3.registration;


import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserRole;
import com.deletet.deletet3.appuser.AppUserService;
import com.deletet.deletet3.registration.token.ConfirmationToken;
import com.deletet.deletet3.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("email is not valid");
        }

        String token;

        AppUser user;
        if(request.getRole()==1)
        {
            user = new AppUser(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword(), AppUserRole.USER);
        }
        else if(request.getRole()==2)
        {
            user = new AppUser(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword(), AppUserRole.COMPANY);
        }
        else
        {
          user=  new AppUser(request.getFirstName(),request.getLastName(),request.getEmail(),request.getPassword(), AppUserRole.ADMIN);
        }

        token= appUserService.signUpUser(user);

        String result = confirmToken(token);

        //return appUserService.signUpUser(user);
        return result;

    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "register successful";
    }
}
