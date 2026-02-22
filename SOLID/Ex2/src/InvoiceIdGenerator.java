public class InvoiceIdGenerator {
    private static int invoiceSeq = 1000;

    public static String generateInvoiceId(){
        String id = "INV-" + (++invoiceSeq);
        return id;
    }
}
