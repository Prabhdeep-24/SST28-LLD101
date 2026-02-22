public class TaxManager {
    public static double taxPercent(String customerType){
        return TaxRules.taxPercent(customerType);
    }

    public static double getTax(double totalAmount, double taxPercent){
        return totalAmount * (taxPercent / 100.0);
    }
}
