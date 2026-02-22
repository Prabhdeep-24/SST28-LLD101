public class PrintInvoice {
    public static void printInvoice(String printable, InvoiceData data, int lines){
        System.out.println(printable);
        System.out.println("Saved invoice: " + data.getInvoiceId() + " (lines=" + lines + ")");
    }
}
