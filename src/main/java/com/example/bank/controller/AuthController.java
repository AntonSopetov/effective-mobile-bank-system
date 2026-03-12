package com.example.bank.controller;

import com.example.bank.security.JwtUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        return jwtUtils.generateToken(username);
    }
}
