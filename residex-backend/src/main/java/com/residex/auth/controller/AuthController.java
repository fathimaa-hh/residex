package com.residex.auth.controller;

import com.residex.auth.dto.AuthResponse;
import com.residex.auth.dto.LoginRequest;
import com.residex.auth.dto.RegisterRequest;
import com.residex.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }
}