package com.deletet.deletet3.registration;


import com.deletet.deletet3.appuser.*;
import com.deletet.deletet3.registration.token.ConfirmationToken;
import com.deletet.deletet3.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
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



    public ResponseEntity<?> register(RegistrationRequest regRequest) {
        boolean isValidEmail = emailValidator.test(regRequest.getEmail());
        if(!isValidEmail)
        {
            throw new IllegalStateException("Email is not valid");
        }

        String token;

        AppUser user;
        if(regRequest.getRole()==1)
        {
            user = new AppUser(regRequest.getFirstName(),regRequest.getLastName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.USER);
        }
        else if(regRequest.getRole()==2)
        {
            user = new AppUser(regRequest.getFirstName(),regRequest.getLastName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.COMPANY);
        }
        else
        {
          user=  new AppUser(regRequest.getFirstName(),regRequest.getLastName(),regRequest.getEmail(),regRequest.getPassword(), AppUserRole.ADMIN);
        }

        token= appUserService.signUpUser(user);

       confirmToken(token);

        return ResponseEntity.ok("User registered successfully");

    }


    public ResponseEntity<AppUserDTO> login (LoginRequest logRequest)  {

        AppUser appUser = appUserService.signInUser(logRequest.getEmail(), logRequest.getPassword());
        if(appUser != null)
        {
            return new ResponseEntity<>(new AppUserDTO(appUser.getId(),appUser.getFirstName(),appUser.getLastName(),appUser.getEmail(),appUser.getPassword(),appUser.getAppUserRole()), HttpStatus.OK);
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
