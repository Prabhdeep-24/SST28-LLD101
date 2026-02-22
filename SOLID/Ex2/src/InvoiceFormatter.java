public class InvoiceFormatter {
    public static String identityFormat(InvoiceData data, MenuRepository menu){
        String invId = data.getInvoiceId();
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : data.getLines()) {
            MenuItem item = menu.getItem(l.itemId);
            double lineTotal = item.price * l.qty;

            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", data.getSubtotal()));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.getTaxPercent(), data.getTax()));
        out.append(String.format("Discount: -%.2f\n", data.getDiscount()));
        out.append(String.format("TOTAL: %.2f\n", data.getTotal()));

        // System.out.println(out);
        return out.toString();
    }
}
