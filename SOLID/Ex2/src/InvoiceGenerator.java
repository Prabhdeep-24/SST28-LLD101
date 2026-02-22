import java.util.List;

public class InvoiceGenerator {
    public static InvoiceData generateInvoice(List<OrderLine> lines, MenuRepository menu, String customerType){
        InvoiceData data = InvoiceCalculator.calcualteData(lines, menu, customerType);
        data.setInvoiceId(InvoiceIdGenerator.generateInvoiceId());

        for (OrderLine l : lines) {
            data.insertLine(l);
        }

        return data;
    }
}
