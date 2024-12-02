package dev.ecagataydogan.orderservice.order.mapper;

import dev.ecagataydogan.orderservice.customer.CustomerResponse;
import dev.ecagataydogan.orderservice.order.dto.request.OrderRequest;
import dev.ecagataydogan.orderservice.order.entity.Order;

public class OrderMapper {

    private OrderMapper() {
    }

    public static Order toEntity(CustomerResponse customerResponse, OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(customerResponse.getId());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        return order;
    }
}
