package dev.ecagataydogan.paymentservice.payment.dto.request;

import dev.ecagataydogan.paymentservice.payment.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long orderId;
}

