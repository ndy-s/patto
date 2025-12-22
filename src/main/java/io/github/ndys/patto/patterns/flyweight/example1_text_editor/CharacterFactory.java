package io.github.ndys.patto.patterns.flyweight.example1_text_editor;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    private static final Map<Character, CharacterFlyweight> cache = new HashMap<>();

    public static CharacterFlyweight getCharacter(char symbol) {
        CharacterFlyweight character = cache.get(symbol);

        if (character == null) {
            character = new ConcreteCharacter(symbol);
            cache.put(symbol, character);
            System.out.println("Created new flyweight for '" + symbol + "'");
        }

        return character;
    }

    public static int getTotalFlyweights() {
        return cache.size();
    }
}

