package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

public class Demo1 {
    public static void run() {
        System.out.println("--- Support Ticket Demo ---");
        TicketDemo ticketDemo = new TicketDemo();

        System.out.println("Demo complete. Press Enter to return...");
        try { System.in.read(); } catch(Exception ignored) {}
    }
    
}

