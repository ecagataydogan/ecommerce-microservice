package dev.ecagataydogan.paymentservice.kafka;

import dev.ecagataydogan.paymentservice.payment.entity.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentConfirmation {

    private Long paymentId;
    private Long orderId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
}
