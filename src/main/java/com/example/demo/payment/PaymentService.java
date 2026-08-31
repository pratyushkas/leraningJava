package com.example.demo.payment;

import com.example.demo.service.NotificationClient;
import com.example.demo.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {

    public final Map<String, PaymentProcessor> paymentProcessor;

    @Autowired
    private NotificationClient notificationClient;

    public PaymentService(Map<String,PaymentProcessor> paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void processPayment(String paymentType) {
        PaymentProcessor paymentProcessor = this.paymentProcessor.get(paymentType);
        System.out.println("Initiating the payment process...");
        paymentProcessor.processPayment();
        notificationClient.notify("Payment Notification");
        System.out.println("Payment process completed.");
    }


}
