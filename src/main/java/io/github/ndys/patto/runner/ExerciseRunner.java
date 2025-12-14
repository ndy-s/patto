package io.github.ndys.patto.runner;

import java.nio.file.Path;
import java.util.Scanner;

public final class ExerciseRunner {

    public static void run(Path root) throws Exception {
        String mainClass = MainClassDetector.detect(root);
        if (mainClass == null)
            throw new IllegalStateException("No main class found");

        ProcessBuilder pb = new ProcessBuilder(
            "java", "-cp", root.toString(), mainClass
        );

        pb.redirectErrorStream(true);
        Process p = pb.start();

        try (Scanner s = new Scanner(p.getInputStream())) {
            while (s.hasNextLine()) System.out.println(s.nextLine());
        }

        p.waitFor();
    }
}

