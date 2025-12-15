package io.github.ndys.patto.exercise;

import java.util.Map;
import java.util.Scanner;

import io.github.ndys.patto.llm.LLMClient;
import io.github.ndys.patto.runner.ExerciseCompiler;
import io.github.ndys.patto.runner.ExerciseRunner;
import io.github.ndys.patto.ui.ExerciseMenu;
import io.github.ndys.patto.ui.Spinner;
import io.github.ndys.patto.ui.TerminalPrinter;

public class ExerciseSession {

    private final ExerciseContext ctx;
    private final LLMClient llm;
    private final Scanner scanner;

    public ExerciseSession(
            ExerciseContext ctx,
            LLMClient llm,
            Scanner scanner
    ) {
        this.ctx = ctx;
        this.llm = llm;
        this.scanner = scanner;
    }

    public void start() {
        TerminalPrinter.printExerciseHeader(ctx);

        while (true) {
            ExerciseMenu.Option option =
                    ExerciseMenu.askOption(scanner);

            switch (option) {
                case ASK_AI -> askAI();
                case RUN -> runCode();
                case SUBMIT -> submit();
                case CANCEL -> {
                    ExerciseCleaner.delete(ctx.root);
                    TerminalPrinter.info("Exercise cancelled and deleted.");
                    return;
                }
            }
        }
    }

    private void askAI() {
        String question = ExerciseMenu.askAIQuestion(scanner);
        if (question == null) return;

        Map<String, Object> answer = Spinner.run(
            "Asking AI",
            () -> llm.askHelp(
                ctx.patternName,
                ctx.instructions.get("instructions").toString(),
                ExerciseMenu.loadCurrentCode(ctx),
                question
            )
        );

        TerminalPrinter.printAIAnswer(answer);
    }

    private void runCode() {
        try {
            TerminalPrinter.info("Compiling exercise...");
            boolean ok = ExerciseCompiler.compile(ctx.root);

            if (!ok) {
                TerminalPrinter.error("Compilation failed.");
                return;
            }

            TerminalPrinter.success("Compilation successful.");
            ExerciseRunner.run(ctx.root);

        } catch (Exception e) {
            TerminalPrinter.error("Run failed: " + e.getMessage());
        }
    }

    private void submit() {
        try {
            String combined = ExerciseMenu.combineSolution(ctx);

            var feedback = Spinner.run(
                "Checking solution",
                () -> llm.checkSolution(
                    ctx.patternName,
                    ctx.instructions,
                    ctx.templates,
                    combined,
                    ctx.difficulty
                )
            );

            TerminalPrinter.printFeedback(feedback);

            if (ExerciseMenu.askFinish(scanner)) {
                ExerciseCleaner.delete(ctx.root);
                TerminalPrinter.success("Exercise finished. Deleted.");
                System.exit(0);
            }

        } catch (Exception e) {
            TerminalPrinter.error("Submit failed: " + e.getMessage());
        }
    }
}


