public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public boolean supports(Notification n){
        if(n.phone == null || !n.phone.startsWith("+")){
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        
        if(n.body == null){
            throw new IllegalArgumentException("Notification cannot be empty");
        }
        
        return true;
    }

    @Override
    public void doSend(Notification n) {
        // LSP violation: tightens precondition
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
