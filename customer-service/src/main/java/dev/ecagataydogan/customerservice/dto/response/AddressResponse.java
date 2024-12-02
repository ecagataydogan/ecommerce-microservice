package dev.ecagataydogan.customerservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private String street;
    private String zipCode;
    private String houseNumber;
}
