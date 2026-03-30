package strategy;

import model.ShowSeat;

public class BasePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(ShowSeat showSeat, double basePrice) {
        // Additional factors could be added here based on SeatType.
        // For simplicity, we just return basePrice + some small factor.
        double multiplier = 1.0;
        switch (showSeat.getSeat().getSeatType()) {
            case SILVER:
                multiplier = 1.0;
                break;
            case GOLD:
                multiplier = 1.5;
                break;
            case PLATINUM:
                multiplier = 2.0;
                break;
        }
        return basePrice * multiplier;
    }
}
