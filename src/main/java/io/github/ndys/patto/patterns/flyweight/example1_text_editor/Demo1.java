package io.github.ndys.patto.patterns.flyweight.example1_text_editor;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Flyweight > Text Editor Characters");

        String text = "HELLO FLYWEIGHT";

        System.out.println("Typing text: " + text);
        System.out.println();

        int position = 0;
        for (char c : text.toCharArray()) {
            CharacterFlyweight character = CharacterFactory.getCharacter(c);
            character.draw(position++);
        }

        System.out.println();
        System.out.println("Total flyweights created: " + CharacterFactory.getTotalFlyweights());
        System.out.println("(Notice repeated characters share the same object)");

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

