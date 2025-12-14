package io.github.ndys.patto.ui;

import io.github.ndys.patto.exercise.DifficultyLevel;
import io.github.ndys.patto.exercise.ExerciseContext;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class TerminalPrinter {

    private TerminalPrinter() {}

    public static void printHeader(String title) {
        int width = 49;
        System.out.println();
        System.out.println("╔" + "═".repeat(width) + "╗");
        System.out.println("║" + center(title, width) + "║");
        System.out.println("╚" + "═".repeat(width) + "╝");
    }

    private static String center(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }

        int left = (width - text.length()) / 2;
        int right = width - text.length() - left;

        return " ".repeat(left) + text + " ".repeat(right);
    }

    public static void printExerciseHeader(ExerciseContext ctx) {
        System.out.println("""
                
                +====================================+
                |        Exercise Generated           |
                +====================================+
                """);
        info("Pattern    : " + ctx.patternName);
        info("Difficulty : " + ctx.difficulty);
        info("Files:");
        ctx.templates.keySet()
            .forEach(f -> System.out.println(" - " + f));
    }

    public static void printExerciseSummary(
        Path root,
        Map<String, Object> instructions,
        Map<String, String> templates,
        DifficultyLevel difficulty
    ) {
        System.out.println("\n+=========== Exercise Generated ===========+");

        if (instructions.containsKey("title")) {
            System.out.println("Title      : " + instructions.get("title"));
        }

        System.out.println("Difficulty : " + difficulty);
        System.out.println("Workspace  : " + root.toAbsolutePath());

        System.out.println("\nInstructions:");
        System.out.println("- " + root.resolve("README.md").toAbsolutePath());

        System.out.println("\nGenerated Files:");
        templates.keySet().stream()
            .sorted()
            .forEach(f ->
                System.out.println(
                    "- " + root.resolve(f).toAbsolutePath()
                )
            );

        System.out.println("\nWhat to do next:");
        System.out.println("""
            1. Open the files above in your editor
            2. Read README.md carefully
            3. Implement the missing logic
            4. Use the menu to:
                - Run your code
                - Ask AI for hints
                - Submit for evaluation
            """);

        System.out.println("+==========================================+");
    }

    public static void printAIAnswer(Map<String, Object> answer) {
        System.out.println("\n+=========== AI Help ===========+");
        String md = (String) answer.get("answer");
        System.out.println(MarkdownTerminalRenderer.render(md));
        System.out.println("+================================+");
    }

    public static void printFeedback(Map<String, Object> fb) {
        System.out.println("\n+=========== Result =============+");

        String status = (String) fb.get("status");
        int score = ((Number) fb.get("score")).intValue();

        System.out.println("Status : " + (status.equals("PASS") ? "✅ PASS" : "⚠️ FAIL"));
        System.out.println("Score  : " + score + "/100\n");

        System.out.println("Feedback:");
        String feedbackMd = String.valueOf(fb.get("feedback"));
        System.out.println(MarkdownTerminalRenderer.render(feedbackMd));

        List<String> suggestions = (List<String>) fb.get("suggestions");

        System.out.println("\nSuggestions:");

        if (suggestions == null || suggestions.isEmpty()) {
            System.out.println("- None 🎉");
        } else {
            String suggestionsMd = suggestions.stream()
                .map(s -> "- " + s)
                .collect(Collectors.joining("\n"));

            System.out.println(
                MarkdownTerminalRenderer.render(suggestionsMd)
            );
        }

        System.out.println("+================================+");
    }

    public static void info(String msg) {
        System.out.println("• " + msg);
    }

    public static void success(String msg) {
        System.out.println("✔ " + msg);
    }

    public static void error(String msg) {
        System.out.println("❌ " + msg);
    }
}


