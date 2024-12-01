package dev.ecagataydogan.customerservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    @NotNull
    private String houseNumber;
}
