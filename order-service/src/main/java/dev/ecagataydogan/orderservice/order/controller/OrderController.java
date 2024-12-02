package dev.ecagataydogan.orderservice.order.controller;

import dev.ecagataydogan.orderservice.order.dto.request.OrderRequest;
import dev.ecagataydogan.orderservice.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(@RequestHeader("X-User-Id") Long userId, @RequestBody @Valid OrderRequest orderRequest) {
        orderService.createOrder(userId, orderRequest);
    }
}
