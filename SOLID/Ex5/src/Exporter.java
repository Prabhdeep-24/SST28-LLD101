public abstract class Exporter {
    // implied "contract" but not enforced (smell)
    public final ExportResult export(ExportRequest req){
        validate(req);

        if(!supports(req)){
            throw new UnsupportedOperationException("Not Supported");
        }

        return doExport(req);
    }

    public boolean supports(ExportRequest req){
        return true;
    }

    protected void validate(ExportRequest req){
        if(req == null) throw new IllegalArgumentException();
    }

    protected abstract ExportResult doExport(ExportRequest req);
}
