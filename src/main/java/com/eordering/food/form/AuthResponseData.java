package com.eordering.food.form;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseData {
    private String message;
    private String token;
}
