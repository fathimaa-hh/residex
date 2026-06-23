package com.residex.auth.controller;

import com.residex.auth.dto.AuthResponse;
import com.residex.auth.dto.LoginRequest;
import com.residex.auth.dto.RegisterRequest;
import com.residex.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(
    name = "Authentication",
    description = "Login and Registration APIs"
)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor


public class AuthController {

    private final AuthService authService;


    @Operation(
        summary = "Register Student"
    )


    @PostMapping("/register")
    public AuthResponse register(
            @Valid
            @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(
            @Valid
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }
}