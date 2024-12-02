package dev.ecagataydogan.orderservice.customer;

import lombok.Data;

@Data
public class AddressResponse {

    private String street;
    private String zipCode;
    private String houseNumber;
}
