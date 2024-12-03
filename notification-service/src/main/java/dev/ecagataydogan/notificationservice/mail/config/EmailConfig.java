package dev.ecagataydogan.notificationservice.mail.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {
    public static final String EMAIL_TASK_EXECUTOR = "emailTaskExecutor";

    @Bean(name = EMAIL_TASK_EXECUTOR)
    Executor emailTaskExecutor() {
        return newFixedThreadPool(8);
    }
}
