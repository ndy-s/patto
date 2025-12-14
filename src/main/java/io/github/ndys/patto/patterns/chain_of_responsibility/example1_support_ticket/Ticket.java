package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

public class Ticket {
    private final String description;
    private final Severity severity;

    public Ticket(String description, Severity severity) {
        this.description = description;
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public Severity getSeverity() {
        return severity;
    }
}
