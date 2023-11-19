package com.eordering.food.service;

import com.eordering.food.config.UserDetailsImpl;
import com.eordering.food.entity.Authority;
import com.eordering.food.entity.User;
import com.eordering.food.form.AuthResponseData;
import com.eordering.food.form.LoginData;
import com.eordering.food.form.SignupData;
import com.eordering.food.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthResponseData> authenticate(LoginData loginData) {
        final String login = loginData.getLogin();
        final String password = loginData.getLogin();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AuthResponseData.builder().message(e.getMessage()).build());
        }

        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with name %s not found", login)));

        String jwt = jwtTokenService.generateToken(new UserDetailsImpl(user));
        return ResponseEntity.status(HttpStatus.OK).body(AuthResponseData.builder().token(jwt).message("You're authorized").build());
    }

    public ResponseEntity<AuthResponseData> register(SignupData signupData) throws AuthenticationException {
        final String login = signupData.getLogin();

        if (userRepository.existsByLogin(login))
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponseData.builder().message(String.format("User with login %s already exists", login)).build());

        final String password = passwordEncoder.encode(signupData.getPassword());

        User user = User.builder()
                .login(login)
                .password(password)
                .authorities(new HashSet<>())
                .enabled(true).build();

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setRole(Authority.Roles.USER);

        user.setAuthorities(Set.of(authority));

        userRepository.save(user);
        String jwt = jwtTokenService.generateToken(new UserDetailsImpl(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthResponseData.builder().token(jwt).message("Success").build());
    }
}
