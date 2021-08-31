package com.deletet.deletet3.registration;


import com.deletet.deletet3.appuser.AppUser;
import com.deletet.deletet3.appuser.AppUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "api/deletet")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;


    @PostMapping("/signin")
    public ResponseEntity<AppUserDTO> signin(@RequestBody LoginRequest logRequest)
    {
        return registrationService.login(logRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest regRequest)
    {
        return registrationService.register(regRequest);
    }


    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
