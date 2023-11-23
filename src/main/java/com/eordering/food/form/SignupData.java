package com.eordering.food.form;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupData {


    @NotBlank(message = "Login cannot be empty")
    @Size(min = 8, max = 64, message = "Login must be between 8 and 64 characters")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~`]*$",
            message = "Login must begin with letter")
    private String login;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Login must be 8 characters or more")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "Password must contain at least one uppercase letter and one lowercase letter")
    private String password;
}
