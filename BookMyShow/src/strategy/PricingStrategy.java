package strategy;

import model.ShowSeat;

public interface PricingStrategy {
    double calculatePrice(ShowSeat showSeat, double basePrice);
}
