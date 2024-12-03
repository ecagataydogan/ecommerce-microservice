package dev.ecagataydogan.notificationservice.mail.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static dev.ecagataydogan.notificationservice.mail.config.EmailConfig.EMAIL_TASK_EXECUTOR;

@Service
@Slf4j
@AllArgsConstructor
public class EmailManager {

    private final JavaMailSender javaMailSender;

    @Async(value = EMAIL_TASK_EXECUTOR)
    public void sendEmail(String fromAddress, String recipientAddress, String subject, String html) {
        log.info("Sending email, recipientAddress: {}", recipientAddress);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromAddress);
            helper.setTo(recipientAddress);
            helper.setSubject(subject);
            helper.setText(html, true);

            javaMailSender.send(mimeMessage);
            log.info("Email sent! recipientAddress: {}", recipientAddress);
        } catch (MailSendException e) {
            log.error("Error sending email, will retry", e);
            throw e;
        } catch (Exception e) {
            log.error("Error sending email", e);
        }
    }
}
