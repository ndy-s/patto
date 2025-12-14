package io.github.ndys.patto.ui;

public class MarkdownTerminalRenderer {
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String ITALIC = "\u001B[3m";

    private static final String BRIGHT_CYAN = "\u001B[96m";
    private static final String BRIGHT_BLUE = "\u001B[94m";
    private static final String BRIGHT_WHITE = "\u001B[97m";
    private static final String BRIGHT_YELLOW = "\u001B[93m";
    private static final String BRIGHT_GREEN = "\u001B[92m";
    private static final String DIM_WHITE = "\u001B[37m";

    private static final String INDENT = "  ";

    public static String render(String markdown) {
        StringBuilder sb = new StringBuilder();
        String[] lines = markdown.split("\n");
        boolean inCodeBlock = false;

        for (String line : lines) {
            String trimmed = line.trim();

            // Toggle fenced code block
            if (trimmed.startsWith("```")) {
                inCodeBlock = !inCodeBlock;
                if (inCodeBlock) sb.append(BRIGHT_GREEN); // start code block
                else sb.append(RESET).append("\n"); // end code block
                continue;
            }

            if (inCodeBlock) {
                sb.append(line).append("\n");
                continue;
            }

            // Headings
            if (trimmed.startsWith("# ")) {
                sb.append("\n").append(BOLD).append(BRIGHT_CYAN).append(trimmed).append(RESET).append("\n");
            } else if (trimmed.startsWith("## ")) {
                sb.append("\n").append(BOLD).append(BRIGHT_BLUE).append(trimmed).append(RESET).append("\n");
            } else if (trimmed.startsWith("### ")) {
                sb.append("\n").append(BOLD).append(BRIGHT_WHITE).append(trimmed).append(RESET).append("\n");
            }
            // Bullets
            else if (trimmed.startsWith("- ") || trimmed.startsWith("* ")) {
                int spaces = line.indexOf(trimmed.charAt(0));
                String indent = INDENT.repeat(spaces / 2);
                sb.append(indent).append(DIM_WHITE).append("- ")
                        .append(processInlineMarkdown(trimmed.substring(2))).append(RESET).append("\n");
            }
            // Numbered list
            else if (trimmed.matches("^\\d+\\.\\s.*")) {
                int spaces = line.indexOf(trimmed.charAt(0));
                String indent = INDENT.repeat(spaces / 2);
                sb.append(indent).append(DIM_WHITE)
                        .append(processInlineMarkdown(trimmed)).append(RESET).append("\n");
            }
            // Paragraph
            else if (!trimmed.isEmpty()) {
                sb.append(processInlineMarkdown(trimmed)).append("\n");
            }
            // Empty line
            else {
                sb.append("\n");
            }
        }

        sb.append(RESET);
        return sb.toString();
    }

    private static String processInlineMarkdown(String line) {
        line = line.replaceAll("\\*\\*(.*?)\\*\\*", BOLD + BRIGHT_WHITE + "$1" + RESET);
        line = line.replaceAll("`(.*?)`", BRIGHT_GREEN + "$1" + RESET);
        line = line.replaceAll("\\*(.*?)\\*", ITALIC + BRIGHT_YELLOW + "$1" + RESET);
        return line;
    }
}

