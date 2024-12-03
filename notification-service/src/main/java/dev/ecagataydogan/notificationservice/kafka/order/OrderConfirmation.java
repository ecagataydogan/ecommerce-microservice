package dev.ecagataydogan.notificationservice.kafka.order;

import dev.ecagataydogan.notificationservice.kafka.payment.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmation {

    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<PurchaseResponse> products;
}
