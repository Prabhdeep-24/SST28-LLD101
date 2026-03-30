import java.util.Map;

public class HourlyPricingStrategy implements PricingStrategy {
    private Map<SlotType, Double> rates;

    public HourlyPricingStrategy(Map<SlotType, Double> rates) {
        this.rates = rates;
    }

    @Override
    public double calculateBill(Ticket ticket, long exitTime) {
        long durationHours = (long) Math.ceil((double)(exitTime - ticket.getEntryTime()) / (1000 * 60 * 60));
        durationHours = Math.max(durationHours, 1); // Minimum 1 hour charge

        double rate = rates.getOrDefault(ticket.getSlot().getType(), 0.0);
        return durationHours * rate;
    }
}
