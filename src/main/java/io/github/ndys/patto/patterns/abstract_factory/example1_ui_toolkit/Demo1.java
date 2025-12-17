package io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Abstract Factory > UI Toolkit");

        UIFactory windowsFactory = new WindowsUIFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.renderUI();

        System.out.println();

        UIFactory macFactory = new MacUIFactory();
        Application macApp = new Application(macFactory);
        macApp.renderUI();

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}
