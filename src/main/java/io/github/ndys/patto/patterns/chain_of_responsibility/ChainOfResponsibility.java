package io.github.ndys.patto.patterns.chain_of_responsibility;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket.Demo1;
import io.github.ndys.patto.llm.GeminiClient;

import static io.github.ndys.patto.patterns.ExerciseUtils.generateAndDoExercise;

public class ChainOfResponsibility {


    public static void show() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Chain of Responsibility Pattern Examples ===");
        System.out.println("1. Support Ticket Handling");
        System.out.println("2. Generate Exercise");
        System.out.println("0. Back");
        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> Demo1.run();
            case 2 -> generateAndDoExercise(scanner, "Chain of Responsibility");
            case 0 -> {}
            default -> System.out.println("Invalid choice!");
        }
    }

}

