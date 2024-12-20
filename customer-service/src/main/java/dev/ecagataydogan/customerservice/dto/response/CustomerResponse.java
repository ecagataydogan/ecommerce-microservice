package dev.ecagataydogan.customerservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponse address;
}
