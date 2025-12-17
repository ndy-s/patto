package io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit;

public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Mac Button");
    }
}
