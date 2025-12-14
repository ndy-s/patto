package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

public class ManagerSupport extends SupportHandler {

    @Override
    protected boolean canHandle(Ticket ticket) {
        return ticket.getSeverity() == Severity.HIGH;
    }

    @Override
    protected void process(Ticket ticket) {
        System.out.println("🔴 Manager handled ticket: " + ticket.getDescription());
    }
}

