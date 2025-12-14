package io.github.ndys.patto.runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainClassDetector {

    public static String detect(Path root) throws IOException {
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
    
}

