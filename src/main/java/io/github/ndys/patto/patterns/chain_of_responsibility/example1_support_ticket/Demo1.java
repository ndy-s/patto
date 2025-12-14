package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

import io.github.ndys.patto.ui.TerminalPrinter;
public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Chain of Responsibility > Support Ticket Handling Demo");

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler manager = new ManagerSupport();

        level1.setNext(level2).setNext(manager);

        Ticket t1 = new Ticket("Password reset", Severity.LOW);
        Ticket t2 = new Ticket("System bug affecting reports", Severity.MEDIUM);
        Ticket t3 = new Ticket("Production server down", Severity.HIGH);

        level1.handle(t1);
        level1.handle(t2);
        level1.handle(t3);
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}

