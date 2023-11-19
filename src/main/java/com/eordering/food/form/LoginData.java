package com.eordering.food.form;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginData {
    private String login;
    private String password;
}
