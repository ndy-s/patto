package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

public abstract class SupportHandler {

    protected SupportHandler nextHandler;

    public SupportHandler setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public void handle(Ticket ticket) {
        if (canHandle(ticket)) {
            process(ticket);
        } else if (nextHandler != null) {
            nextHandler.handle(ticket);
        } else {
            System.out.println("❌ Ticket could not be handled: " + ticket.getDescription());
        }
    }

    protected abstract boolean canHandle(Ticket ticket);
    protected abstract void process(Ticket ticket);
}

