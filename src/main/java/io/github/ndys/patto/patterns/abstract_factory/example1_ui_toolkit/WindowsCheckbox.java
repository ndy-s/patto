package io.github.ndys.patto.patterns.abstract_factory.example1_ui_toolkit;

public class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Windows Checkbox");
    }
}
