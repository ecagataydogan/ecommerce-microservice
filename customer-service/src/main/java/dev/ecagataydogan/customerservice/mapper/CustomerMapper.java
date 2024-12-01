package dev.ecagataydogan.customerservice.mapper;

import dev.ecagataydogan.customerservice.dto.request.OnboardRequest;
import dev.ecagataydogan.customerservice.entity.Customer;

public class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer fromRequest(Long userId, OnboardRequest onboardRequest) {
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setFirstName(onboardRequest.getFirstName());
        customer.setLastName(onboardRequest.getLastName());
        customer.setEmail(onboardRequest.getEmail());
        customer.setAddress(AddressMapper.fromRequest(onboardRequest.getAddress()));
        return customer;
    }
}
