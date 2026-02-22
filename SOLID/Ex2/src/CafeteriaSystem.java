import java.util.*;

public class CafeteriaSystem {
    private final MenuRepository menu;
    private final FileRespository store;

    public CafeteriaSystem(MenuRepository menu, FileRespository store){
        this.menu = menu;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.add(i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        InvoiceData data = InvoiceGenerator.generateInvoice(lines, menu, customerType);
        String printable = InvoiceFormatter.identityFormat(data, menu);

        store.save(data.getInvoiceId(), printable);
        int linesCount = store.countLines(data.getInvoiceId());

        PrintInvoice.printInvoice(printable, data, linesCount);
    }
}
