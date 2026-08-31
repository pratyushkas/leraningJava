package com.example.demo.web.controller;

import com.example.demo.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{paymentType}")
    public String processPayment(@PathVariable String paymentType) {
        paymentService.processPayment(paymentType);
        return "Payment processed successfully.";
    }
}
