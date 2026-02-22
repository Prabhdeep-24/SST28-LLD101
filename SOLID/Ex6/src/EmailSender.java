public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public boolean supports(Notification n){
        if(n.email == null){
            throw new IllegalArgumentException("email cannot be null");
        }

        if(n.subject == null){
            throw new IllegalArgumentException("notification subject cannot be empty");
        }

        return n.body.length() <= 40; // can change this in the notification object and there only you 
        // can make the input body of length at max 40
    }

    @Override
    public void doSend(Notification n) {
        // LSP smell: truncates silently, changing meaning
        String body = n.body;
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + body);
        audit.add("email sent");
    }
}
