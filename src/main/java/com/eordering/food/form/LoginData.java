package com.eordering.food.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {
    @NotBlank(message = "Login cannot be empty")
    private String login;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
