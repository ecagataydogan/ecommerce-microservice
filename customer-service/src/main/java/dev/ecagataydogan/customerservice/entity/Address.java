package dev.ecagataydogan.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

        private String street;
        private String zipCode;
        private String houseNumber;
}
