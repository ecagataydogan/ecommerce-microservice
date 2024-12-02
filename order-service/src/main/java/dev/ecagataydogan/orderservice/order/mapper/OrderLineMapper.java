package dev.ecagataydogan.orderservice.order.mapper;

import dev.ecagataydogan.orderservice.order.entity.Order;
import dev.ecagataydogan.orderservice.order.entity.OrderLine;
import dev.ecagataydogan.orderservice.product.PurchaseResponse;

import java.math.BigDecimal;

public class OrderLineMapper {

    private OrderLineMapper() {
    }

    public static OrderLine toEntity(Order order, PurchaseResponse purchaseResponse, BigDecimal amount) {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setProductId(purchaseResponse.getProductId());
        orderLine.setQuantity(purchaseResponse.getQuantity());
        orderLine.setAmount(amount);
        return orderLine;
    }
}
