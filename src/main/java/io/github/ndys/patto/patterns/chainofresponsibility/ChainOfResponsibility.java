package io.github.ndys.patto.patterns.chainofresponsibility;

import java.util.Scanner;

import io.github.ndys.patto.patterns.chainofresponsibility.example1_support_ticket.ChainOfResponsibilityDemo1;

public class ChainOfResponsibility {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Chain of Responsibility Pattern Examples ===");
        System.out.println("1. Support Ticket Handling");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> ChainOfResponsibilityDemo1.run();
            case 0 -> {}
            default -> System.out.println("Invalid choice!");
        }
    }
}


