package dev.ecagataydogan.authservice.security.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyLoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String identifier;
}
