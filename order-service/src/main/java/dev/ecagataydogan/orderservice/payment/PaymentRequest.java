package dev.ecagataydogan.orderservice.payment;

import dev.ecagataydogan.orderservice.order.entity.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequest {

    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Long orderId;
}
