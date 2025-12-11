package io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket;

import java.util.Scanner;
import io.github.ndys.patto.utils.MenuUtils;

public class Demo1 {

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printHeader("Chain of Responsibility > Support Ticket Demo");

        TicketDemo ticketDemo = new TicketDemo();

        System.out.println("\nDemo complete. Press Enter to return to the menu...");
        scanner.nextLine();
    }
}

