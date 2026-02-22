import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult doExport(ExportRequest req) {
        // LSP issue: changes meaning by lossy conversion
        String csv = "title,body\n"
                + escape(req.title) + ","
                + escape(req.body) + "\n";

        
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String value) {
        if (value == null) return "";

        String escaped = value.replace("\"", "\"\"");

        return "\"" + escaped + "\"";
    }
}
