package dev.ecagataydogan.customerservice.mapper;

import dev.ecagataydogan.customerservice.dto.request.AddressRequest;
import dev.ecagataydogan.customerservice.entity.Address;

public class AddressMapper {

    private AddressMapper() {
    }

    public static Address fromRequest(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        address.setHouseNumber(addressRequest.getHouseNumber());
        return address;
    }
}
