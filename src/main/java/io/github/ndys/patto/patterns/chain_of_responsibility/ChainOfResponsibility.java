package io.github.ndys.patto.patterns.chain_of_responsibility;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

import io.github.ndys.patto.patterns.chain_of_responsibility.example1_support_ticket.Demo1;
import io.github.ndys.patto.llm.GeminiClient;

public class ChainOfResponsibility {

    private static final String EXERCISE_DIR = "src/main/java/io/github/ndys/patto/exercise/";

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

    private static void generateAndDoExercise(Scanner scanner, String patternName) {
        try {
            Path exerciseRoot = Paths.get(EXERCISE_DIR);
            Files.createDirectories(exerciseRoot);

            String instructions = GeminiClient.generateExerciseInstructions(patternName);
            Path readmeFile = exerciseRoot.resolve("README.md");
            Files.writeString(readmeFile, instructions);

            Map<String, String> templates = GeminiClient.generateExerciseTemplates(patternName, instructions);

            String packageLine = "package io.github.ndys.patto.exercise;\n\n";
            for (Map.Entry<String, String> entry : templates.entrySet()) {
                Path filePath = exerciseRoot.resolve(entry.getKey());
                Files.createDirectories(filePath.getParent());
                String content = entry.getValue().startsWith("package") ? entry.getValue() : packageLine + entry.getValue();
                Files.writeString(filePath, content);
            }

            System.out.println("\nExercise generated:");
            System.out.println("Instructions: " + readmeFile);
            System.out.println("Files:");
            templates.keySet().forEach(f -> System.out.println("- " + exerciseRoot.resolve(f)));

            System.out.println("\nPress Enter when you have completed the exercise...");
            scanner.nextLine();

            StringBuilder combinedSolution = new StringBuilder();
            for (Map.Entry<String, String> entry : templates.entrySet()) {
                Path filePath = exerciseRoot.resolve(entry.getKey());
                combinedSolution.append(Files.readString(filePath)).append("\n\n");
            }

            String feedback = GeminiClient.checkSolution(instructions, templates, combinedSolution.toString());
            System.out.println("\n=== Feedback from LLM ===");
            System.out.println(feedback);

            Files.walk(exerciseRoot)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

