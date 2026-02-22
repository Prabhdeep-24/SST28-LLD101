public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    public final void send(Notification n){
        validate(n);

        if(!supports(n)){
            throw new RuntimeException("Invalid Data");
        }

        doSend(n);
    }

    public boolean supports(Notification n){
        return true;
    }

    protected void validate(Notification n) {
        if (n == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }
    }

    public abstract void doSend(Notification n);
}
