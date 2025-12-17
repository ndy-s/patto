package io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit;

public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.render();;
        checkbox.render();
    }
}
