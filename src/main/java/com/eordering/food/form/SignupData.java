package com.eordering.food.form;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupData {
    private String login;
    private String email;
    private String password;
}
