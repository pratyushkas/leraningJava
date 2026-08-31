package com.example.demo.service.impl;

import com.example.demo.service.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component("paypal")
public class PayPalPayment implements PaymentProcessor {
    @Override
    public void processPayment() {
        System.out.println("PayPalPayment processPayment");
    }
}
