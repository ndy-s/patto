package io.github.ndys.patto.patterns.decorator.example1_coffee_customization;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 10.0;
    }

    
}

