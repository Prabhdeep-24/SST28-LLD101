package com.example.tickets;

public class TicketService {

    // ===== Create =====
    public IncidentTicket createTicket(String id, String reporterEmail, String title) {

        // No scattered validation here anymore.
        // Builder handles validation.

        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    // ===== Escalate =====
    public IncidentTicket escalateToCritical(IncidentTicket t) {

        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    // ===== Assign =====
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {

        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}