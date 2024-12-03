package dev.ecagataydogan.notificationservice.kafka.order;

import lombok.Data;

@Data
public class CustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponse address;
}
