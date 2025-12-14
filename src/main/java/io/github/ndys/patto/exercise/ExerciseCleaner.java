package io.github.ndys.patto.exercise;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public final class ExerciseCleaner {

    private ExerciseCleaner() {}

    public static void delete(Path root) {
        try {
            if (!Files.exists(root)) return;

            Files.walk(root)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        } catch (Exception ignored) {
        }
    }
}


