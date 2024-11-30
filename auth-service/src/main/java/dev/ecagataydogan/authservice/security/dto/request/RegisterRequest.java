package dev.ecagataydogan.authservice.security.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotEmpty
    @Email
    private String email;

    private String password;
}
