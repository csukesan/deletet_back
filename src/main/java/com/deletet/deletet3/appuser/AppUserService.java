package com.deletet.deletet3.appuser;

import com.deletet.deletet3.registration.token.ConfirmationToken;
import com.deletet.deletet3.registration.token.ConfirmationTokenService;
import com.deletet.deletet3.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    private final static String USER_NOT_FOUND_MSG =
            "User with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email)  {
        return (UserDetails) appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUser loadUser(String email)
    {
        return (AppUser) appUserRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUser signInUser(String email,String password)
    {
        boolean userExists = appUserRepository
                .findByEmail(email)
                .isPresent();

        if(userExists)
        {
            UserDetails user = loadUserByUsername(email);
            if(passwordEncoder.matches(password,user.getPassword()))
            {
                AppUser appUser = loadUser(email);
                return appUser;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        //appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

}
