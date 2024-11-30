package dev.ecagataydogan.gateway.filter;

import dev.ecagataydogan.gateway.service.JwtTokenService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtTokenService jwtTokenService;

    public AuthenticationFilter(JwtTokenService jwtTokenService) {
        super(Config.class);
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new AuthenticationGatewayFilter(jwtTokenService);
    }

    public static class Config {
    }
}