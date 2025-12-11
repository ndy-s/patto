package io.github.ndys.patto.app;

import java.util.Scanner;
import io.github.ndys.patto.patterns.chain_of_responsibility.ChainOfResponsibility;
import io.github.ndys.patto.utils.MenuUtils;

public class Menu {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            MenuUtils.printHeader("Main Menu");

            System.out.println("1. Chain of Responsibility");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> ChainOfResponsibility.show("Main Menu > Chain of Responsibility");
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

