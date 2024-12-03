package dev.ecagataydogan.orderservice.order.service;

import dev.ecagataydogan.orderservice.customer.CustomerClient;
import dev.ecagataydogan.orderservice.customer.CustomerResponse;
import dev.ecagataydogan.orderservice.exception.BusinessException;
import dev.ecagataydogan.orderservice.exception.ErrorCode;
import dev.ecagataydogan.orderservice.kafka.OrderConfirmation;
import dev.ecagataydogan.orderservice.kafka.OrderProducer;
import dev.ecagataydogan.orderservice.order.dto.request.OrderRequest;
import dev.ecagataydogan.orderservice.order.entity.Order;
import dev.ecagataydogan.orderservice.order.entity.OrderLine;
import dev.ecagataydogan.orderservice.order.mapper.OrderLineMapper;
import dev.ecagataydogan.orderservice.order.mapper.OrderMapper;
import dev.ecagataydogan.orderservice.order.repository.OrderRepository;
import dev.ecagataydogan.orderservice.payment.PaymentClient;
import dev.ecagataydogan.orderservice.payment.PaymentRequest;
import dev.ecagataydogan.orderservice.product.ProductClient;
import dev.ecagataydogan.orderservice.product.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderProducer orderProducer;

    public void createOrder(Long userId, OrderRequest orderRequest) {
        try {
            CustomerResponse customer = customerClient.getCustomer(userId);
            List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(orderRequest.getProducts());

            Order order = OrderMapper.toEntity(customer, orderRequest);
            List<OrderLine> orderLines = createOrderLines(purchasedProducts, order);
            order.setOrderLines(orderLines);
            orderRepository.save(order);

            PaymentRequest paymentRequest = PaymentRequest.builder()
                    .amount(calculateTotalAmount(orderLines))
                    .paymentMethod(orderRequest.getPaymentMethod())
                    .orderId(order.getId())
                    .build();
            paymentClient.createPayment(paymentRequest);

            OrderConfirmation orderConfirmation = OrderConfirmation.builder()
                    .orderId(order.getId())
                    .totalAmount(calculateTotalAmount(orderLines))
                    .paymentMethod(orderRequest.getPaymentMethod())
                    .customer(customer)
                    .products(purchasedProducts)
                    .build();
            orderProducer.sendOrderConfirmation(orderConfirmation);

            // TODO: exception handling must improve
            /*
             * Global FeignError Handler
             * Fallback mechanism for Feign Clients
             * https://claude.ai/chat/e52b798b-b1ff-4b8d-b094-5aff7b47214e
             * */
        } catch (BusinessException e) {
            log.error("Error occurred while creating order", e);
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while creating order", e);
            throw new BusinessException(ErrorCode.internal_error, "Error occurred while creating order");
        }
    }

    public List<OrderLine> createOrderLines(List<PurchaseResponse> purchasedProducts, Order order) {
        return purchasedProducts.stream()
                .map(product -> {
                    BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
                    return OrderLineMapper.toEntity(order, product, amount);
                })
                .toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(OrderLine::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
