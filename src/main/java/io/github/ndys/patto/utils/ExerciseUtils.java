package io.github.ndys.patto.utils;

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
    private static final LLMClient llmClient = new GeminiClient();

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

                Path relativePath = exerciseRoot.relativize(filePath.getParent());

                String packageName = basePackage;

                if (relativePath.getNameCount() > 0) {
                    String raw = relativePath.toString().replace(File.separatorChar, '.');
                    if (!raw.equals(".") && !raw.isBlank()) {
                        packageName = basePackage + "." + raw;
                    }
                }

                String content = entry.getValue().trim();

                content = content.replaceFirst("^package\\s+[^;]+;", "");
                content = "package " + packageName + ";\n\n" + content;

                content = content.replaceAll(
                    "(?m)^import\\s+((?!java\\.|javax\\.|org\\.|com\\.).+);",
                    "import " + basePackage + ".$1;"
                );

                Files.writeString(filePath, content);
            }

            System.out.println("\n+================ Exercise Generated ================+");
            System.out.println("Title       : " + exerciseTitle);
            System.out.println("Instructions: " + readmeFile.toAbsolutePath());
            System.out.println("Files:");
            templates.keySet().forEach(f ->
                    System.out.println(" - " + exerciseRoot.resolve(f).toAbsolutePath())
            );
            System.out.println("+===================================================+");

            label:
            while (true) {
                System.out.println("\n+================ Exercise Options =================+");
                System.out.println("1. Ask AI About This Exercise");
                System.out.println("2. Run Exercise Code");
                System.out.println("3. Submit Solution for Checking");
                System.out.println("0. Cancel and Delete Exercise");
                System.out.print("Enter your choice: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        askAIAboutExercise(scanner, patternName, instructionsObj, templates, exerciseRoot);
                        break;
                    case "2":
                        runExerciseProgram(exerciseRoot);
                        break;
                    case "3": {
                        StringBuilder combinedSolution = new StringBuilder();
                        for (Map.Entry<String, String> entry : templates.entrySet()) {
                            Path filePath = exerciseRoot.resolve(entry.getKey());
                            combinedSolution.append(Files.readString(filePath)).append("\n\n");
                        }

                        Map<String, Object> feedbackObj = runWithSpinner(
                            () -> llmClient.checkSolution(
                                patternName,
                                instructionsObj,
                                templates,
                                combinedSolution.toString()
                            ),
                            "Checking solution and generating feedback"
                        );

                        System.out.println("\n+================ LLM Feedback =================+");
                        if (feedbackObj != null) {
                            StringBuilder md = new StringBuilder();
                            md.append("## Exercise: ").append(feedbackObj.get("exerciseTitle")).append("\n\n");
                            md.append("### Feedback:\n").append(feedbackObj.get("feedback")).append("\n\n");
                            md.append("### Suggestions:\n").append(feedbackObj.get("suggestions")).append("\n");

                            String rendered = MarkdownTerminalRenderer.render(md.toString());
                            System.out.println(rendered);
                        } else {
                            System.out.println("Failed to retrieve feedback from LLM.");
                        }
                        System.out.println("+===============================================+");

                        while (true) {
                            System.out.println("\n+============== What next? ==============+");
                            System.out.println("1. Back to Exercise Options");
                            System.out.println("2. Finish & Delete Exercise");
                            System.out.print("Choose: ");

                            String choice = scanner.nextLine().trim();

                            if (choice.equals("1")) {
                                continue label;   
                            }

                            if (choice.equals("2")) {
                                deleteExerciseFolder(exerciseRoot);
                                System.out.println("Exercise finished and deleted. Returning...");
                                return;
                            }

                            System.out.println("Invalid choice. Try again.");
                        }
                    }
                    case "0":
                        deleteExerciseFolder(exerciseRoot);
                        System.out.println("Exercise cancelled. Returning...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void askAIAboutExercise(
            Scanner scanner,
            String patternName,
            Map<String, Object> instructionsObj,
            Map<String, String> templates,
            Path exerciseRoot
    ) {
        try {
            System.out.println("\nType your question to the AI (or type 'cancel'): ");
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("cancel")) {
                System.out.println("Cancelled asking AI.");
                return;
            }

            // Load exercise file contents
            Map<String, String> codeFiles = new java.util.HashMap<>();
            for (String file : templates.keySet()) {
                Path path = exerciseRoot.resolve(file);
                codeFiles.put(file, Files.readString(path));
            }

            String instructions = (String) instructionsObj.get("instructions");

            Map<String, Object> answer = runWithSpinner(
            () -> llmClient.askHelp(
                    patternName,
                    instructions,
                    codeFiles,
                    question
                ),
                "Asking AI for help"
            );

            if (answer == null) {
                System.out.println("AI could not respond.");
                return;
            }

            System.out.println("\n+================ AI Help =================+");
            String rawMd = (String) answer.get("answer");
            String rendered = MarkdownTerminalRenderer.render(rawMd);
            System.out.println(rendered);
            System.out.println("+==========================================+");
        } catch (Exception e) {
            System.out.println("Error asking AI: " + e.getMessage());
        }
    }

    private static void deleteExerciseFolder(Path exerciseRoot) {
        try {
            if (Files.exists(exerciseRoot)) {
                Files.walk(exerciseRoot)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (Exception ignored) {}
    }

    private static void runExerciseProgram(Path exerciseRoot) {
        try {
            System.out.println("\nCompiling exercise...");

            var javaFiles = Files.walk(exerciseRoot)
                .filter(p -> p.toString().endsWith(".java"))
                .map(Path::toString)
                .toList();

            if (javaFiles.isEmpty()) {
                System.out.println("No Java files found to compile.");
                return;
            }

            ProcessBuilder pbCompile = new ProcessBuilder();
            pbCompile.command().add("javac");
            pbCompile.command().add("-d");
            pbCompile.command().add(exerciseRoot.toString());
            pbCompile.command().addAll(javaFiles);

            pbCompile.redirectErrorStream(true);
            Process compile = pbCompile.start();
            compile.waitFor();

            try (Scanner s = new Scanner(compile.getInputStream())) {
                boolean hasOutput = false;
                while (s.hasNextLine()) {
                    System.out.println(s.nextLine());
                    hasOutput = true;
                }
                if (!hasOutput && compile.exitValue() != 0) {
                    System.out.println("Compilation failed with unknown errors.");
                }
            }

            if (compile.exitValue() != 0) {
                System.out.println("\n❌ Compilation failed. Fix the errors and try again.");
                return;
            }

            System.out.println("\n✔ Compilation successful. Searching for main class...");

            String mainClass = detectMainClass(exerciseRoot);

            if (mainClass == null) {
                System.out.println("❌ No class with a main method found.");
                System.out.println("Add a class containing:");
                System.out.println("  public static void main(String[] args)");
                return;
            }

            System.out.println("➡ Running: " + mainClass + "\n");

            ProcessBuilder pbRun = new ProcessBuilder(
                "java",
                "-cp", exerciseRoot.toString(),
                mainClass
            );

            pbRun.redirectErrorStream(true);
            Process run = pbRun.start();

            try (Scanner s = new Scanner(run.getInputStream())) {
                while (s.hasNextLine()) System.out.println(s.nextLine());
            }

            run.waitFor();

        } catch (Exception e) {
            System.out.println("Unexpected error while running exercise: " + e.getMessage());
        }
    }

    private static String detectMainClass(Path root) throws IOException {
        for (Path p : (Iterable<Path>) Files.walk(root)::iterator) {
            if (p.toString().endsWith(".java")) {
                String content = Files.readString(p);

                if (content.contains("public static void main(String[] args)")) {
                    String pkg = "";
                    var pkgMatch = java.util.regex.Pattern.compile(
                        "^package\\s+([a-zA-Z0-9_.]+);",
                        java.util.regex.Pattern.MULTILINE
                    ).matcher(content);

                    if (pkgMatch.find()) {
                        pkg = pkgMatch.group(1) + ".";
                    }

                    String className = p.getFileName().toString().replace(".java", "");

                    return pkg + className;
                }
            }
        }
        return null;
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

