package io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit;

public class MacUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }

}
