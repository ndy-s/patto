package io.github.ndys.patto.exercise;

import java.nio.file.Path;
import java.util.Map;

public class ExerciseContext {
    public final String patternName;
    public final Path root;
    public final DifficultyLevel difficulty;

    public final Map<String, Object> instructions;
    public final Map<String, String> templates;

    public ExerciseContext(
        String patternName,
        Path root,
        DifficultyLevel difficulty,
        Map<String, Object> instructions,
        Map<String, String> templates
    ) {
        this.patternName = patternName;
        this.root = root;
        this.difficulty = difficulty;
        this.instructions = instructions;
        this.templates = templates;
    }
}

