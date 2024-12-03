package dev.ecagataydogan.notificationservice.kafka.order;

import dev.ecagataydogan.notificationservice.kafka.order.dto.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {

    @KafkaListener(topics = "order-topic")
    public void consume(OrderConfirmation orderConfirmation) {
        log.info("Consumed order confirmation: {}", orderConfirmation);
    }
}
