package io.github.ndys.patto.console;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class MarkdownTerminalRenderer {

    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";

    public static String render(String markdown) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();

        Node document = parser.parse(markdown);
        StringBuilder sb = new StringBuilder();

        renderNode(document, sb);
        return sb.toString();
    }

    private static void renderNode(Node node, StringBuilder sb) {
        if (node instanceof Heading) {
            Heading h = (Heading) node;
            sb.append("\n")
              .append(BOLD).append(CYAN)
              .append("#".repeat(h.getLevel())).append(" ")
              .append(h.getText()).append(RESET)
              .append("\n");
        }

        else if (node instanceof Paragraph) {
            sb.append("\n");
        }

        else if (node instanceof StrongEmphasis) {
            sb.append(BOLD);
        }

        else if (node instanceof Emphasis) {
            sb.append(YELLOW);
        }

        else if (node instanceof FencedCodeBlock) {
            FencedCodeBlock cb = (FencedCodeBlock) node;
            sb.append("\n")
                .append(GREEN)
                .append(cb.getContentChars().toString())
                .append(RESET)
                .append("\n");
        }

        // Text nodes
        if (node instanceof Text) {
            sb.append(((Text) node).getChars());
        }

        // Render children
        for (Node child : node.getChildren()) {
            renderNode(child, sb);
        }

        // Reset after emphasis
        if (node instanceof StrongEmphasis || node instanceof Emphasis) {
            sb.append(RESET);
        }
    }
}


