package com.eordering.food.config;

import com.eordering.food.entity.Authority;
import com.eordering.food.entity.User;
import com.eordering.food.repository.AuthorityRepository;
import com.eordering.food.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;


@Component
@AllArgsConstructor
class InitializerForAdmin implements CommandLineRunner {
    final UserRepository userRepository;
    final AuthorityRepository authorityRepository;
    final PasswordEncoder passwordEncoder;
    @Value("${admin.login}")
    @Nullable
    private String login;
    @Value("${admin.password}")
    @Nullable
    private String password;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByLogin(login)) {
            User admin = User.builder()
                    .login(login)
                    .password(passwordEncoder.encode(password))
                    .email("vlad123@mail.ru")
                    .authorities(new HashSet<>())
                    .enabled(true).build();

            Authority adminAuthority = new Authority();
            adminAuthority.setRole(Authority.Roles.ADMIN);
            adminAuthority.setUser(admin);

            admin.getAuthorities().add(adminAuthority);

            userRepository.save(admin);
        }
    }
}
