package com.example.session17.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void processPayment(Object payment) {
        if (payment instanceof CreditCardPayment) {
            // process credit card payment
        } else if (payment instanceof CashPayment) {
            // process cash payment
        }
        // ...more payment types
    }
}
