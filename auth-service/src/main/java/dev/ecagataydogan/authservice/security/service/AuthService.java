package dev.ecagataydogan.authservice.security.service;

import dev.ecagataydogan.authservice.security.dto.request.LoginRequest;
import dev.ecagataydogan.authservice.security.dto.request.RefreshTokenRequest;
import dev.ecagataydogan.authservice.security.dto.request.RegisterRequest;
import dev.ecagataydogan.authservice.security.dto.response.RefreshTokenResponse;
import dev.ecagataydogan.authservice.security.dto.response.VerifyLoginResponse;
import dev.ecagataydogan.authservice.security.entity.RefreshToken;
import dev.ecagataydogan.authservice.user.entity.User;
import dev.ecagataydogan.authservice.user.entity.UserDetailsImpl;
import dev.ecagataydogan.authservice.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final AccessTokenService accessTokenService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }

    public VerifyLoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        String accessToken = accessTokenService.generateAccessToken(authentication);
        return VerifyLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .id(userDetails.getId())
                .identifier(userDetails.getUsername())
                .build();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String requestRefreshToken = refreshTokenRequest.getRefreshToken();
        String accessToken = refreshTokenService.refreshToken(requestRefreshToken);
        return RefreshTokenResponse.builder()
                .refreshToken(requestRefreshToken)
                .accessToken(accessToken)
                .build();
    }
}
