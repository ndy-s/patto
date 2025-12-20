package io.github.ndys.patto.patterns.decorator.example1_coffee_customization;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Decorator > Coffee Customization");

        Coffee coffee = new SimpleCoffee();
        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);

        System.out.println("\nAfter customization:");
        System.out.println("Order: " + coffee.getDescription());
        System.out.println("Cost: $" + coffee.getCost());
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

