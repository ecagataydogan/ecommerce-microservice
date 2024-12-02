package dev.ecagataydogan.orderservice.kafka;

import dev.ecagataydogan.orderservice.customer.CustomerResponse;
import dev.ecagataydogan.orderservice.order.entity.PaymentMethod;
import dev.ecagataydogan.orderservice.product.PurchaseResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderConfirmation {

    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<PurchaseResponse> products;
}
