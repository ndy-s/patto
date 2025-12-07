package io.github.ndys.patto.patterns.chainofresponsibility.example1_support_ticket;

public class ChainOfResponsibilityDemo1 {
    public static void run() {
        System.out.println("--- Support Ticket Demo ---");
        TicketDemo ticketDemo = new TicketDemo();

        System.out.println("Demo complete. Press Enter to return...");
        try { System.in.read(); } catch(Exception ignored) {}
    }
    
}

