package com.example.payments;

public class SafeCashSDK {

    public String process(String customerId, int amountCents) {
        return "SC-" + customerId + "-" + amountCents;
    }
}