package dev.ecagataydogan.paymentservice.payment.service;

import dev.ecagataydogan.paymentservice.kafka.PaymentProducer;
import dev.ecagataydogan.paymentservice.payment.dto.request.PaymentRequest;
import dev.ecagataydogan.paymentservice.payment.entity.Payment;
import dev.ecagataydogan.paymentservice.payment.mapper.PaymentMapper;
import dev.ecagataydogan.paymentservice.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    public void createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(PaymentMapper.toEntity(paymentRequest));
        paymentProducer.sendPaymentConfirmationNotification(PaymentMapper.toPaymentConfirmation(payment));
    }
}
