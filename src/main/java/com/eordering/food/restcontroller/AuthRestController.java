package com.eordering.food.restcontroller;

import com.eordering.food.form.AuthResponseData;
import com.eordering.food.form.LoginData;
import com.eordering.food.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthRestController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginData loginData) {
        ResponseEntity<AuthResponseData> responseEntity;
        try {
            responseEntity = authenticationService.authenticate(loginData);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponseData.builder().message(e.getMessage()).build());
        }

        return responseEntity;
    }
}
