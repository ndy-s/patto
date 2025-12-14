package io.github.ndys.patto.exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ExerciseIO {

    private static final String BASE_PACKAGE = "io.github.ndys.patto.workspace";

    public static Path initExerciseRoot(String dir) throws IOException {
        Path root = Paths.get(dir);
        Files.createDirectory(root);
        return root;
    }

    public static void writeReadme(Path root, String context) throws IOException {
        Files.writeString(root.resolve("README.md"), context);
    }

    public static void writeTemplates(Path root, Map<String, String> templates) throws IOException {
        for (var entry : templates.entrySet()) {
            writeTemplates(root, entry.getKey(), entry.getValue());
        }
    }

    private static void writeTemplates(Path root, String relativePath, String rawContent) throws IOException {
        Path file = root.resolve(relativePath);
        Files.createDirectories(file.getParent());

        String pkg = resolvePackage(root, file.getParent());
        String content = normalizeContent(rawContent, pkg);

        Files.writeString(file, content);
    }

    private static String resolvePackage(Path root, Path dir) {
        Path relative = root.relativize(dir);

        if (relative.getNameCount() > 0) {
            String raw = relative.toString().replace(File.separatorChar, '.');

            if (!raw.equals(".") && !raw.isBlank()) {
                return BASE_PACKAGE + "." + raw;
            }
        }

        return BASE_PACKAGE;
        // if (relative.getNameCount() == 0) return BASE_PACKAGE;
        // return BASE_PACKAGE + "." + relative.toString().replace(File.separatorChar, '.');
    }

    private static String normalizeContent(String content, String pkg) {
        content = content.replaceFirst("^package\\s+[^;]+;", "");
        content = "package " + pkg + ";\n\n" + content.trim();

        return content.replaceAll(
            "(?m)^import\\s+((?!java\\.|javax\\.|org\\.|com\\.).+);",
            "import " + BASE_PACKAGE + ".$1;"
        );
    }
    
}

