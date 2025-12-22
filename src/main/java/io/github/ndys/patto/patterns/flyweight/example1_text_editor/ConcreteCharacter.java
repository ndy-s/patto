package io.github.ndys.patto.patterns.flyweight.example1_text_editor;

public class ConcreteCharacter implements CharacterFlyweight {

    private final char symbol;

    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void draw(int position) {
        System.out.println("Drawing character '" + symbol + "' at position " + position);
    }

    
}

