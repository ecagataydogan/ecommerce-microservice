package dev.ecagataydogan.notificationservice.kafka.order.dto;

import dev.ecagataydogan.notificationservice.kafka.payment.dto.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmation {

    private Long orderId;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<PurchaseResponse> products;
}
