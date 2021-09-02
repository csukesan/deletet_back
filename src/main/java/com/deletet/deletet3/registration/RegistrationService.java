package com.deletet.deletet3.registration;


import com.deletet.deletet3.Profile.CompanyProfile;
import com.deletet.deletet3.Profile.Profile;
import com.deletet.deletet3.Profile.ProfileDTO;
import com.deletet.deletet3.Profile.ProfileRepository;
import com.deletet.deletet3.appuser.*;
import com.deletet.deletet3.registration.token.ConfirmationToken;
import com.deletet.deletet3.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    AuthenticationManager authenticationManager;
    private final ProfileRepository profileRepository;
    //private finalProfileDTO profileDTO;



    public ResponseEntity<AppUser> register(RegistrationRequest regRequest) {

        boolean isValidEmail = emailValidator.test(regRequest.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email is not valid");
        }

        String token;

        AppUser user;
        if(regRequest.getRole()==1)
        {
            user = new AppUser(regRequest.getFullName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.USER);
        }
        else if(regRequest.getRole()==2)
        {
            user = new AppUser(regRequest.getFullName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.COMPANY);
        }
        else
        {
          user=  new AppUser(regRequest.getFullName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.ADMIN);
        }

        token= appUserService.signUpUser(user);

        confirmToken(token);

        if(regRequest.getRole()==1)
        {
            Profile profile = new Profile(user.getId(),user.getFullName(),user.getEmail());
            profile = profileRepository.save(profile);
        }
        if(regRequest.getRole()==2)
        {
            CompanyProfile profile = new CompanyProfile(user.getId(),user.getEmail(),user.getFullName());
        }


        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    public ResponseEntity<AppUserDTO> login (LoginRequest logRequest)  {

        AppUser appUser = appUserService.signInUser(logRequest.getEmail(), logRequest.getPassword());
        if(appUser != null)
        {
            return new ResponseEntity<>(new AppUserDTO(appUser.getId(),appUser.getFullName(),appUser.getEmail(),appUser.getPassword(),appUser.getAppUserRole()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }

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
