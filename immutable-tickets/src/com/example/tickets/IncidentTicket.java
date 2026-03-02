package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority;
    private final List<String> tags;
    private final String assigneeEmail;
    private final boolean customerVisible;
    private final Integer slaMinutes;
    private final String source;

    private IncidentTicket(Builder b) {
        this.id = b.id;
        this.reporterEmail = b.reporterEmail;
        this.title = b.title;
        this.description = b.description;
        this.priority = b.priority;
        this.tags = List.copyOf(b.tags); // defensive copy
        this.assigneeEmail = b.assigneeEmail;
        this.customerVisible = b.customerVisible;
        this.slaMinutes = b.slaMinutes;
        this.source = b.source;
    }

    public String getId() { return id; }
    public String getReporterEmail() { return reporterEmail; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public List<String> getTags() { return tags; }
    public String getAssigneeEmail() { return assigneeEmail; }
    public boolean isCustomerVisible() { return customerVisible; }
    public Integer getSlaMinutes() { return slaMinutes; }
    public String getSource() { return source; }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return IncidentTicket.builder()
            .id(this.id)
            .reporterEmail(this.reporterEmail)
            .title(this.title)
            .description(this.description)
            .priority(this.priority)
            .assigneeEmail(this.assigneeEmail)
            .customerVisible(this.customerVisible)
            .slaMinutes(this.slaMinutes)
            .source(this.source)
            .tags(this.tags);
    }

    public static class Builder {

        private String id;
        private String reporterEmail;
        private String title;

        private String description;
        private String priority = "LOW";
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private boolean customerVisible;
        private Integer slaMinutes;
        private String source;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder addTag(String tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = new ArrayList<>(tags);
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(boolean visible) {
            this.customerVisible = visible;
            return this;
        }

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public IncidentTicket build() {

            // Required validation
            if (id == null || id.isBlank() || id.length() > 20 || !id.matches("[A-Z0-9-]+")) {
                throw new IllegalArgumentException("Invalid id");
            }

            if (reporterEmail == null || !reporterEmail.contains("@")) {
                throw new IllegalArgumentException("Invalid reporter email");
            }

            if (title == null || title.isBlank() || title.length() > 80) {
                throw new IllegalArgumentException("Invalid title");
            }

            if (assigneeEmail != null && !assigneeEmail.contains("@")) {
                throw new IllegalArgumentException("Invalid assignee email");
            }

            if (priority != null &&
                    !(priority.equals("LOW") ||
                      priority.equals("MEDIUM") ||
                      priority.equals("HIGH") ||
                      priority.equals("CRITICAL"))) {
                throw new IllegalArgumentException("Invalid priority");
            }

            if (slaMinutes != null &&
                    (slaMinutes < 5 || slaMinutes > 7200)) {
                throw new IllegalArgumentException("Invalid SLA minutes");
            }

            return new IncidentTicket(this);
        }
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }
}