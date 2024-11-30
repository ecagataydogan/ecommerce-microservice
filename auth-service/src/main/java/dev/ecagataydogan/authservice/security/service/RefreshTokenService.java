package dev.ecagataydogan.authservice.security.service;

import dev.ecagataydogan.authservice.security.config.JwtConfig;
import dev.ecagataydogan.authservice.security.entity.RefreshToken;
import dev.ecagataydogan.authservice.security.repository.RefreshTokenRepository;
import dev.ecagataydogan.authservice.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenService accessTokenService;
    private final JwtConfig jwtConfig;
    private final UserService userService;

    public String refreshToken(String token) {
        return findByToken(token)
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> accessTokenService.generateToken(user.getEmail()))
                .orElseThrow(() -> new RuntimeException("Refresh token does not exist."));
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plus(Duration.ofMillis(jwtConfig.getJwtRefreshExpirationMs())));
        refreshToken.setUser(userService.getUser(userId));
        return refreshTokenRepository.save(refreshToken);
    }

    private Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expired. Please make a new login request.");

        }
        return token;
    }
}

