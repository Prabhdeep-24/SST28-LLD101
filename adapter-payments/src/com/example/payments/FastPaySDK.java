package com.example.payments;

public class FastPaySDK {
    public String makePayment(String customerId, int amountCents) {
        return "FP-" + customerId + "-" + amountCents;
    }
}