package io.github.ndys.patto.app;

import java.util.Scanner;

import io.github.ndys.patto.patterns.chain_of_responsibility.ChainOfResponsibility;

public class Menu {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Patto: Design Pattern Playground ===");
            System.out.println("1. Chain of Responsibility");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> ChainOfResponsibility.show();
                default -> System.out.println("Invalid input, try again!");
            }

        }

    }
    
}
