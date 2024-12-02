package dev.ecagataydogan.paymentservice.payment.mapper;

import dev.ecagataydogan.paymentservice.kafka.PaymentConfirmation;
import dev.ecagataydogan.paymentservice.payment.dto.request.PaymentRequest;
import dev.ecagataydogan.paymentservice.payment.entity.Payment;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static Payment toEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setOrderId(paymentRequest.getOrderId());
        return payment;
    }

    public static PaymentConfirmation toPaymentConfirmation(Payment payment) {
        return PaymentConfirmation.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .build();
    }
}
