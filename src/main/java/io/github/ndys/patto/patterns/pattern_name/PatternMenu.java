package io.github.ndys.patto.patterns.pattern_name;

import java.util.Scanner;
import io.github.ndys.patto.patterns.pattern_name.example1_context.DemoClass;

public class PatternMenu {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== <PatternName> Pattern Examples ===");
        System.out.println("1. <Example Context 1>");
        System.out.println("2. <Example Context 2>");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> DemoClass.run();
            case 2 -> DemoClass.run();
            case 0 -> {}
            default -> System.out.println("Invalid choice!");
        }
    }
}



