package io.github.ndys.patto.patterns.abstract_factory;

import io.github.ndys.patto.exercise.ExerciseFacade;
import io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit.Demo1;
import io.github.ndys.patto.ui.TerminalPrinter;

import java.util.Scanner;

public class AbstractFactory {

    public static void show(String menuPath) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalPrinter.printHeader(menuPath);

            System.out.println("1. Practice Exercise");
            System.out.println("2. View Example: UI Toolkit");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> ExerciseFacade.start(scanner, "Abstract Factory");
                case 2 -> Demo1.run();
                case 0 -> {
                    return;
                }
                default -> System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }
}

