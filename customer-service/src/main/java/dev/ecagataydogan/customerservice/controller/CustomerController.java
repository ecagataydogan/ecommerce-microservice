package dev.ecagataydogan.customerservice.controller;

import dev.ecagataydogan.customerservice.dto.request.OnboardRequest;
import dev.ecagataydogan.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/onboard")
    public void onboard(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody OnboardRequest onboardRequest) {
        customerService.onboard(userId, onboardRequest);
    }
}
