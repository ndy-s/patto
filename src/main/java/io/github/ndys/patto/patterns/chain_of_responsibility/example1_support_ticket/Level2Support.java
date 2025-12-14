package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

public class Level2Support extends SupportHandler {

    @Override
    protected boolean canHandle(Ticket ticket) {
        return ticket.getSeverity() == Severity.MEDIUM;
    }

    @Override
    protected void process(Ticket ticket) {
        System.out.println("🟡 Level 2 Support handled ticket: " + ticket.getDescription());
    }
    
}

