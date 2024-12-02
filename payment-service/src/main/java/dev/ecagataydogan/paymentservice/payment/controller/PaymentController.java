package dev.ecagataydogan.paymentservice.payment.controller;

import dev.ecagataydogan.paymentservice.payment.dto.request.PaymentRequest;
import dev.ecagataydogan.paymentservice.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
    }
}
