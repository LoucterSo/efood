package com.eordering.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/app")
public class HomeController {
    @GetMapping({"", "/"})
    public String showLoginForm(Principal principal) {
        if (principal == null) {
            return "access-denied";
        }

        return "home";
    }
}
