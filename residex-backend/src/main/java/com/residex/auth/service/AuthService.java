package com.residex.auth.service;

import com.residex.auth.dto.AuthResponse;
import com.residex.auth.dto.LoginRequest;
import com.residex.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}