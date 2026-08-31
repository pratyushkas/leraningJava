package com.example.demo.service.impl;

import com.example.demo.service.PaymentProcessor;
import org.springframework.stereotype.Component;

@Component("creditcard")
public class CreditCardPayment implements PaymentProcessor {

    @Override
    public void processPayment() {
        System.out.println("PaymentProcessor processPayment by CreditCardPayment");
    }
}
