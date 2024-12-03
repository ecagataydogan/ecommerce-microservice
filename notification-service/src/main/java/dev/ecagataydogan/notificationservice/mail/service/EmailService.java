package dev.ecagataydogan.notificationservice.mail.service;

import dev.ecagataydogan.notificationservice.kafka.order.dto.OrderConfirmation;
import dev.ecagataydogan.notificationservice.kafka.payment.dto.PaymentConfirmation;
import dev.ecagataydogan.notificationservice.mail.config.MailConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailTemplateRenderer authEmailTemplateRenderer;
    private final EmailManager emailManager;
    private final MailConfig mailConfig;

    public void sendPaymentConfirmationEmail(String recipientAddress, PaymentConfirmation paymentConfirmation) {
        String fromAddress = mailConfig.getFrom();
        String subject = "e-commerce | Payment Confirmation";
        String html = authEmailTemplateRenderer.renderPaymentConfirmationEmail(paymentConfirmation);
        emailManager.sendEmail(fromAddress, recipientAddress, subject, html);
    }

    public void sendOrderConfirmationEmail(String recipientAddress, OrderConfirmation orderConfirmation) {
        String fromAddress = mailConfig.getFrom();
        String subject = "e-commerce | Order Confirmation";
        String html = authEmailTemplateRenderer.renderOrderConfirmationEmail(orderConfirmation);
        emailManager.sendEmail(fromAddress, recipientAddress, subject, html);
    }
}
