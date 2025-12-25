package io.github.ndys.patto.app;

import java.util.Scanner;

import io.github.ndys.patto.patterns.abstract_factory.AbstractFactory;
import io.github.ndys.patto.patterns.adapter.Adapter;
import io.github.ndys.patto.patterns.bridge.Bridge;
import io.github.ndys.patto.patterns.builder.Builder;
import io.github.ndys.patto.patterns.chain_of_responsibility.ChainOfResponsibility;
import io.github.ndys.patto.patterns.command.Command;
import io.github.ndys.patto.patterns.composite.Composite;
import io.github.ndys.patto.patterns.decorator.Decorator;
import io.github.ndys.patto.patterns.facade.Facade;
import io.github.ndys.patto.patterns.factory_method.FactoryMethod;
import io.github.ndys.patto.patterns.flyweight.Flyweight;
import io.github.ndys.patto.patterns.prototype.Prototype;
import io.github.ndys.patto.patterns.proxy.Proxy;
import io.github.ndys.patto.patterns.singleton.Singleton;
import io.github.ndys.patto.ui.TerminalPrinter;

public class Menu {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalPrinter.printHeader("Design Patterns Playground");

            System.out.println("""
                ╔══════════════ Creational Patterns ══════════════╗
                │  1. Factory Method                              │
                │  2. Abstract Factory                            │
                │  3. Builder                                     │
                │  4. Prototype                                   │
                │  5. Singleton                                   │
                ╠══════════════ Structural Patterns ══════════════╣
                │  6. Adapter                                     │
                │  7. Bridge                                      │
                │  8. Composite                                   │
                │  9. Decorator                                   │
                │ 10. Facade                                      │
                │ 11. Flyweight                                   │
                │ 12. Proxy                                       │
                ╠══════════════ Behavioral Patterns ══════════════╣
                │ 13. Chain of Responsibility                     │
                │ 14. Command                                     │
                │ 15. Iterator                                    │
                │ 16. Mediator                                    │
                │ 17. Memento                                     │
                │ 18. Observer                                    │
                │ 19. State                                       │
                │ 20. Strategy                                    │
                │ 21. Template Method                             │
                │ 22. Visitor                                     │
                ╚═════════════════════════════════════════════════╝
                
                0. Exit
                """);

            System.out.print("Select a pattern: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> FactoryMethod.show("Main Menu > Factory Method");
                case "2" -> AbstractFactory.show("Main Menu > Abstract Factory");
                case "3" -> Builder.show("Main Menu > Builder");
                case "4" -> Prototype.show("Main Menu > Prototype");
                case "5" -> Singleton.show("Main Menu > Singleton");
                case "6" -> Adapter.show("Main Menu > Adapter");
                case "7" -> Bridge.show("Main Menu > Bridge");
                case "8" -> Composite.show("Main Menu > Composite");
                case "9" -> Decorator.show("Main Menu > Decorator");
                case "10" -> Facade.show("Main Menu > Facade");
                case "11" -> Flyweight.show("Main Menu > Flyweight");
                case "12" -> Proxy.show("Main Menu > Proxy");
                case "13" -> ChainOfResponsibility.show("Main Menu > Chain of Responsibility");
                case "14" -> Command.show("Main Menu > Command");

                case "0" -> {
                    System.out.println("Exiting...👋 Goodbye!");
                    return;
                }

                default -> {
                    System.out.println("Invalid choice.");
                    pause(scanner);
                }
            }
        }
    }

    private static void pause(Scanner scanner) {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}


