package io.github.ndys.patto.runner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ExerciseCompiler {

    public static boolean compile(Path root) throws Exception {
        var javaFiles = Files.walk(root)
            .filter(p -> p.toString().endsWith(".java"))
            .map(Path::toString)
            .toList();

        if (javaFiles.isEmpty()) return false;

        ProcessBuilder pb = new ProcessBuilder();
        pb.command().addAll(
            java.util.List.of("javac", "-d", root.toString())
        );
        pb.command().addAll(javaFiles);
        pb.redirectErrorStream(true);

        Process p = pb.start();
        p.waitFor();

        try (Scanner s = new Scanner(p.getInputStream())) {
            while (s.hasNextLine()) System.out.println(s.nextLine());
        }

        return p.exitValue() == 0;
    }

}

