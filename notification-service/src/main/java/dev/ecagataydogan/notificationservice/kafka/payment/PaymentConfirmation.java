package dev.ecagataydogan.notificationservice.kafka.payment;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class PaymentConfirmation {

    private Long paymentId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
}
