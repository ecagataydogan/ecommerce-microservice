package dev.ecagataydogan.customerservice.service;

import dev.ecagataydogan.customerservice.dto.request.OnboardRequest;
import dev.ecagataydogan.customerservice.dto.response.CustomerResponse;
import dev.ecagataydogan.customerservice.entity.Customer;
import dev.ecagataydogan.customerservice.exception.BusinessException;
import dev.ecagataydogan.customerservice.exception.ErrorCode;
import dev.ecagataydogan.customerservice.mapper.CustomerMapper;
import dev.ecagataydogan.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void onboard(Long userId, OnboardRequest onboardRequest) {

        Optional<Customer> optCustomer = customerRepository.findByUserId(userId);
        if (optCustomer.isPresent()) {
            throw new BusinessException(ErrorCode.account_already_exists, "Customer already exists");
        }
        Customer customer = CustomerMapper.fromRequest(userId, onboardRequest);
        customerRepository.save(customer);
    }

    public CustomerResponse getCustomer(Long userId) {
        return customerRepository.findByUserId(userId)
                .map(CustomerMapper::toResponse)
                .orElseThrow(() -> new BusinessException(ErrorCode.not_found, "Customer not found"));
    }
}
