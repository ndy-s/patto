package io.github.ndys.patto.exercise;

import java.nio.file.Path;
import java.util.Scanner;

import io.github.ndys.patto.llm.GeminiClient;
import io.github.ndys.patto.llm.LLMClient;
import io.github.ndys.patto.ui.Spinner;
import io.github.ndys.patto.ui.TerminalPrinter;
import io.github.ndys.patto.ui.ExerciseMenu;

public class ExerciseFacade {

    private static final String EXERCISE_DIR = "src/main/java/io/github/ndys/patto/workspace/";

    private static final LLMClient llm = new GeminiClient();

    public static void start(Scanner scanner, String pattern) {
        try {
            Path root = ExerciseIO.initExerciseRoot(EXERCISE_DIR);

            var instructions = Spinner.run(
                "Generating instructions",
                () -> llm.generateInstructions(pattern)
            );

            DifficultyLevel difficulty = ExerciseMenu.askDifficulty(scanner);

            var templates = Spinner.run(
                "Generating templates",
                () -> llm.generateTemplates(pattern, instructions, difficulty)
            );

            ExerciseIO.writeReadme(root, instructions.get("instructions").toString());
            ExerciseIO.writeTemplates(root, templates);

            TerminalPrinter.printExerciseSummary(root, instructions, templates, difficulty);

            ExerciseContext ctx = new ExerciseContext(pattern, root, difficulty, instructions, templates);

            ExerciseMenu.loop(scanner, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


