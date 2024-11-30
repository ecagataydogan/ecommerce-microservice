package dev.ecagataydogan.authservice.security.service;


import dev.ecagataydogan.authservice.security.config.JwtConfig;
import dev.ecagataydogan.authservice.user.entity.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class AccessTokenService {

    private final JwtConfig jwtConfig;

    public String generateAccessToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfig.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret())
                .compact();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtConfig.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecret())
                .compact();
    }
}
