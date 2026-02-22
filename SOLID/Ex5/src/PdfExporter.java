import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {

    @Override
    public boolean supports(ExportRequest req){
        if(req.body.length() > 20){
            throw new UnsupportedOperationException("PDF cannot handle content > 20 chars");
        }
        
        return true;
    }
    
    @Override
    public ExportResult doExport(ExportRequest req) {
        // LSP violation: tightens precondition arbitrarily
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
