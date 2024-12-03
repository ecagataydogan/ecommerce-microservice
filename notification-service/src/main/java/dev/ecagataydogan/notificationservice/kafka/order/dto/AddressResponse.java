package dev.ecagataydogan.notificationservice.kafka.order.dto;

import lombok.Data;

@Data
public class AddressResponse {

    private String street;
    private String zipCode;
    private String houseNumber;
}
