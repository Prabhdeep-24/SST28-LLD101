package com.bms.strategy;

import com.bms.model.ShowSeat;

public interface PricingStrategy {
    double calculatePrice(ShowSeat showSeat, double basePrice);
}
