package io.github.ndys.patto.ui;

import io.github.ndys.patto.exercise.DifficultyLevel;
import io.github.ndys.patto.exercise.ExerciseCleaner;
import io.github.ndys.patto.exercise.ExerciseContext;
import io.github.ndys.patto.llm.GeminiClient;
import io.github.ndys.patto.llm.LLMClient;
import io.github.ndys.patto.runner.ExerciseCompiler;
import io.github.ndys.patto.runner.ExerciseRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class ExerciseMenu {

    private static final LLMClient llm = new GeminiClient();

    public enum Option {
        ASK_AI, RUN, SUBMIT, CANCEL
    }

    private ExerciseMenu() {}

    public static void loop(Scanner scanner, ExerciseContext ctx) {
        while (true) {
            Option option = askOption(scanner);

            try {
                switch (option) {

                    case ASK_AI -> {
                        String question = askAIQuestion(scanner);
                        if (question == null) break;

                        Map<String, String> code =
                            loadCurrentCode(ctx);

                        var answer = Spinner.run(
                            "Asking AI",
                            () -> llm.askHelp(
                                ctx.patternName,
                                (String) ctx.instructions.get("instructions"),
                                code,
                                question
                            )
                        );

                        TerminalPrinter.printAIAnswer(answer);
                    }

                    case RUN -> {
                        System.out.println("\nCompiling exercise...");
                        boolean compiled =
                            ExerciseCompiler.compile(ctx.root);

                        if (!compiled) {
                            System.out.println("❌ Compilation failed.");
                            break;
                        }

                        System.out.println("Running program...\n");
                        ExerciseRunner.run(ctx.root);
                    }

                    case SUBMIT -> {
                        String solution = combineSolution(ctx);

                        var feedback = Spinner.run(
                            "Checking solution",
                            () -> llm.checkSolution(
                                ctx.patternName,
                                ctx.instructions,
                                ctx.templates,
                                solution,
                                ctx.difficulty
                            )
                        );

                        TerminalPrinter.printFeedback(feedback);

                        if (askFinish(scanner)) {
                            ExerciseCleaner.delete(ctx.root);
                            System.out.println("Exercise finished. Returning...");
                            return;
                        }
                    }

                    case CANCEL -> {
                        ExerciseCleaner.delete(ctx.root);
                        System.out.println("Exercise cancelled. Returning...");
                        return;
                    }
                }

            } catch (Exception e) {
                System.out.println("⚠ Error: " + e.getMessage());
            }
        }
    }

    public static DifficultyLevel askDifficulty(Scanner s) {
        System.out.println("""

            +========= Select Difficulty =========+
            1. Guided   (structure + TODOs)
            2. Minimal  (instructions only)
            +=====================================+
            """);
        System.out.print("Choice: ");

        return switch (s.nextLine().trim()) {
            case "1" -> DifficultyLevel.GUIDED;
            case "2" -> DifficultyLevel.MINIMAL;
            default -> {
                System.out.println("Invalid choice. Try again.");
                yield askDifficulty(s);
            }
        };
    }

    public static Option askOption(Scanner s) {
        System.out.println("""
                
                +========= Exercise Options =========+
                1. Ask AI about this exercise
                2. Run exercise code
                3. Submit solution
                0. Cancel and delete exercise
                +====================================+
                """);
        System.out.print("Choice: ");

        return switch (s.nextLine().trim()) {
            case "1" -> Option.ASK_AI;
            case "2" -> Option.RUN;
            case "3" -> Option.SUBMIT;
            case "0" -> Option.CANCEL;
            default -> {
                System.out.println("Invalid choice.");
                yield askOption(s);
            }
        };
    }

    public static String askAIQuestion(Scanner s) {
        System.out.print("\nAsk AI (or type 'cancel'): ");
        String q = s.nextLine().trim();
        return q.equalsIgnoreCase("cancel") ? null : q;
    }

    public static boolean askFinish(Scanner s) {
        System.out.print("\nFinish & delete exercise? (y/n): ");
        return s.nextLine().trim().equalsIgnoreCase("y");
    }

    public static Map<String, String> loadCurrentCode(ExerciseContext ctx) {
        try {
            Map<String, String> files = new HashMap<>();
            for (String f : ctx.templates.keySet()) {
                Path p = ctx.root.resolve(f);
                files.put(f, Files.readString(p));
            }
            return files;
        } catch (Exception e) {
            return Map.of();
        }
    }

    public static String combineSolution(ExerciseContext ctx) throws Exception {
        StringBuilder sb = new StringBuilder();

        Files.walk(ctx.root)
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .forEach(p -> {
                try {
                    sb.append("// File: ")
                        .append(ctx.root.relativize(p))
                        .append("\n");
                    sb.append(Files.readString(p))
                        .append("\n\n");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        return sb.toString();
    }


}


