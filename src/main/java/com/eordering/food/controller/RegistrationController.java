package com.eordering.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class RegistrationController {
    @GetMapping("/signup")
    public String showRegistrationForm() {

        return "signup";
    }

}
