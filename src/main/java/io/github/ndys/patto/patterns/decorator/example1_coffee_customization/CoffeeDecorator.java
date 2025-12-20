package io.github.ndys.patto.patterns.decorator.example1_coffee_customization;

public abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    protected CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
}

