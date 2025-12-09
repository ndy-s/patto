package io.github.ndys.patto.patterns;

import io.github.ndys.patto.llm.GeminiClient;
import io.github.ndys.patto.llm.LLMClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class ExerciseUtils {
    private static final String EXERCISE_DIR = "src/main/java/io/github/ndys/patto/exercise/";
    private static LLMClient llmClient = new GeminiClient();

    public static void generateAndDoExercise(Scanner scanner, String patternName) {
        try {
            Path exerciseRoot = Paths.get(EXERCISE_DIR);
            Files.createDirectories(exerciseRoot);

            Map<String, Object> instructionsObj = runWithSpinner(
                    () -> llmClient.generateInstructions(patternName),
                    "Generating exercise instructions"
            );

            if (instructionsObj == null) {
                System.out.println("Failed to generate instructions. Aborting exercise.");
                return;
            }

            String exerciseTitle = instructionsObj.get("title").toString();
            String instructionsText = instructionsObj.get("instructions").toString();

            Path readmeFile = exerciseRoot.resolve("README.md");
            Files.writeString(readmeFile, instructionsText);

            Map<String, String> templates = runWithSpinner(
                    () -> llmClient.generateTemplates(patternName, instructionsObj),
                    "Generating template files"
            );

            if (templates == null || templates.isEmpty()) {
                System.out.println("Failed to generate templates. Aborting exercise.");
                return;
            }

            String basePackage = "io.github.ndys.patto.exercise";

            for (Map.Entry<String, String> entry : templates.entrySet()) {
                Path filePath = exerciseRoot.resolve(entry.getKey());
                Files.createDirectories(filePath.getParent());

                // Determine package line based on subfolders
                Path relativePath = exerciseRoot.relativize(filePath.getParent());
                String packageName = basePackage;
                if (relativePath.getNameCount() > 0) {
                    packageName += "." + relativePath.toString().replace(File.separatorChar, '.');
                }

                String content = entry.getValue();
                if (!content.startsWith("package")) {
                    content = "package " + packageName + ";\n\n" + content;
                }

                Files.writeString(filePath, content);
            }

            System.out.println("\nExercise generated: " + exerciseTitle);
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

            Map<String, Object> feedbackObj = runWithSpinner(
                    () -> llmClient.checkSolution(patternName, instructionsObj, templates, combinedSolution.toString()),
                    "Checking solution and generating feedback"
            );

            if (feedbackObj != null) {
                System.out.println("\n=== Feedback from LLM ===");
                System.out.println("Exercise: " + feedbackObj.get("exerciseTitle"));
                System.out.println("Feedback: " + feedbackObj.get("feedback"));
                System.out.println("Suggestions: " + feedbackObj.get("suggestions"));
            } else {
                System.out.println("Failed to retrieve feedback from LLM.");
            }

            Files.walk(exerciseRoot)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static <T> T runWithSpinner(Supplier<T> task, String message) {
        final boolean[] done = {false};
        Thread spinnerThread = new Thread(() -> {
            String[] spinner = {"|", "/", "-", "\\"};
            int i = 0;
            while (!done[0]) {
                System.out.print("\r" + message + " " + spinner[i++ % spinner.length]);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ignored) {

                }
            }
            System.out.print("\r" + message + " ✅\n");
        });

        spinnerThread.start();
        T result = null;
        try {
            result = task.get();
        } finally {
            done[0] = true;
            try {
                spinnerThread.join();
            } catch (InterruptedException ignored) {

            }
        }
        return result;
    }

}

