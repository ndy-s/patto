package io.github.ndys.patto.patterns.builder.example1_computer_builder;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Builder > Computer Builder");

        Director director = new Director();

        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director.construct(gamingBuilder);
        Computer gamingPc = gamingBuilder.getResult();

        System.out.println("Gaming PC:");
        System.out.println(gamingPc);

        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        director.construct(officeBuilder);
        Computer officePc = officeBuilder.getResult();

        System.out.println("Office PC:");
        System.out.println(officePc);

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

