package io.github.ndys.patto.patterns.chain_of_responsibility;

import java.util.Scanner;
import static io.github.ndys.patto.utils.ExerciseUtils.generateAndDoExercise;

import io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket.Demo1;
import io.github.ndys.patto.utils.MenuUtils;

public class ChainOfResponsibility {

    public static void show(String menuPath) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            MenuUtils.printHeader(menuPath);

            System.out.println("1. Generate Exercise");
            System.out.println("2. Support Ticket Handling Example");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> generateAndDoExercise(scanner, "Chain of Responsibility");
                case 2 -> Demo1.run();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

