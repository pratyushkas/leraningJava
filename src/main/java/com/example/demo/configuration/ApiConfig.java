package com.example.demo.configuration;

import com.example.demo.service.NotificationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public NotificationClient notificationClient(@Value("${notification.apiKey}") String apiKey) {
        return new NotificationClient(apiKey,5000);
    }
}
