package io.github.ndys.patto.patterns.chainofresponsibility;

import java.util.Scanner;

import io.github.ndys.patto.patterns.chainofresponsibility.example1_support_ticket.TicketDemo;
import io.github.ndys.patto.patterns.chainofresponsibility.example2_validation_pipeline.ValidationDemo;
import io.github.ndys.patto.patterns.chainofresponsibility.example3_logging_chain.LoggingDemo;

public class ChainOfResponsibility {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Chain of Responsibility Pattern Examples ===");
        System.out.println("1. Support Ticket Handling");
        System.out.println("2. Input Validation Pipeline");
        System.out.println("3. Logging Chain");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> TicketDemo.run();
            case 2 -> ValidationDemo.run();
            case 3 -> LoggingDemo.run();
            case 0 -> {}
            default -> System.out.println("Invalid choice!");
        }
    }
}


