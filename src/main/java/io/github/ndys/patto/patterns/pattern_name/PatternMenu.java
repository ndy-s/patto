package io.github.ndys.patto.patterns.pattern_name;

import java.util.Scanner;
import io.github.ndys.patto.patterns.pattern_name.example1_context.Demo1;
import io.github.ndys.patto.ui.TerminalPrinter;

import static io.github.ndys.patto.utils.ExerciseUtils.generateAndDoExercise;

public class PatternMenu {

    public static void show(String menuPath) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalPrinter.printHeader(menuPath);

            System.out.println("1. Generate Exercise");
            System.out.println("1. Context 1 Example");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> generateAndDoExercise(scanner, "");
                case 2 -> Demo1.run();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

