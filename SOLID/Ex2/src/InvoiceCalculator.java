import java.util.List;

public class InvoiceCalculator {

    public static InvoiceData  calcualteData(List<OrderLine> lines, MenuRepository menu, String customerType){
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.getItem(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }

        double taxPercent = TaxManager.taxPercent(customerType);
        double tax = TaxManager.getTax(subtotal, taxPercent);

        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        return new InvoiceData(subtotal, taxPercent, tax, discount, total);
    }

}
