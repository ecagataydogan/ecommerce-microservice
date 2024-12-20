package dev.ecagataydogan.notificationservice.kafka.payment.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class PaymentConfirmation {

    private Long paymentId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
}
