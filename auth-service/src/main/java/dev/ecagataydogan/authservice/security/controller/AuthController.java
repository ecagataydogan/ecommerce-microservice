package dev.ecagataydogan.authservice.security.controller;

import dev.ecagataydogan.authservice.security.dto.request.LoginRequest;
import dev.ecagataydogan.authservice.security.dto.request.RefreshTokenRequest;
import dev.ecagataydogan.authservice.security.dto.request.RegisterRequest;
import dev.ecagataydogan.authservice.security.dto.response.RefreshTokenResponse;
import dev.ecagataydogan.authservice.security.dto.response.VerifyLoginResponse;
import dev.ecagataydogan.authservice.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public VerifyLoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
