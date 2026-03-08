package com.example.payments;

import java.util.Objects;

public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    @Override
    public String charge(String customerId, int amountCents) {

        Objects.requireNonNull(customerId, "customerId cannot be null");

        return client.payNow(customerId, amountCents);
    }
}