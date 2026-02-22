import java.util.ArrayList;
import java.util.List;

public class InvoiceData {
    private final double subtotal, taxPercent, tax, discount, total;
    private String invoiceId;
    private List<OrderLine> invoiceLines;

    public InvoiceData(double subtotal, double taxPercent, double tax, double discount, double total){
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
        invoiceLines = new ArrayList<>();
    }

    public double getSubtotal(){
        return subtotal;
    }

    public double getTaxPercent(){
        return taxPercent;
    }

    public double getTax(){
        return tax;
    }

    public double getDiscount(){
        return discount;
    }

    public double getTotal(){
        return total;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void insertLine(OrderLine line){
        invoiceLines.add(line);
    }

    public List<OrderLine> getLines(){
        return invoiceLines;
    }

    public String getInvoiceId(){
        return invoiceId;
    }
}
