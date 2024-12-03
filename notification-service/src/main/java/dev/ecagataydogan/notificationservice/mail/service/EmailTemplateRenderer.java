package dev.ecagataydogan.notificationservice.mail.service;

import dev.ecagataydogan.notificationservice.kafka.order.dto.OrderConfirmation;
import dev.ecagataydogan.notificationservice.kafka.payment.dto.PaymentConfirmation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@AllArgsConstructor
public class EmailTemplateRenderer {

    private final TemplateEngine templateEngine;

    public String renderOrderConfirmationEmail(OrderConfirmation orderConfirmation) {
        Context context = new Context();
        context.setVariable("customerName", orderConfirmation.getCustomer().getFirstName() + " " + orderConfirmation.getCustomer().getLastName());
        context.setVariable("orderReference", orderConfirmation.getOrderId());
        context.setVariable("products", orderConfirmation.getProducts());
        context.setVariable("totalAmount", orderConfirmation.getTotalAmount());
        return templateEngine.process("emails/order-confirmation", context);
    }

    public String renderPaymentConfirmationEmail(PaymentConfirmation paymentConfirmation) {
        Context context = new Context();
        context.setVariable("amount", paymentConfirmation.getAmount());
        context.setVariable("orderReference", paymentConfirmation.getOrderId());
        return templateEngine.process("emails/payment-confirmation", context);
    }
}
