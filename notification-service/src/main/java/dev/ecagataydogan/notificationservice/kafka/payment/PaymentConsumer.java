package dev.ecagataydogan.notificationservice.kafka.payment;

import dev.ecagataydogan.notificationservice.kafka.payment.dto.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    @KafkaListener(topics = "payment-topic")
    public void consume(PaymentConfirmation paymentConfirmation) {
        log.info("Consumed payment confirmation: {}", paymentConfirmation);
    }
}
