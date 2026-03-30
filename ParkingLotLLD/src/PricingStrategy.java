public interface PricingStrategy {
    double calculateBill(Ticket ticket, long exitTime);
}
