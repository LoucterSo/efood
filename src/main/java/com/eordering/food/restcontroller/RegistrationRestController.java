package com.eordering.food.restcontroller;

import com.eordering.food.form.AuthResponseData;
import com.eordering.food.form.SignupData;
import com.eordering.food.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class RegistrationRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseData> signup(@RequestBody SignupData signupData) { //Validation

        return authenticationService.register(signupData);
    }
}
