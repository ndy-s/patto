package io.github.ndys.patto.app;

import java.util.Scanner;

import io.github.ndys.patto.patterns.abstract_factory.AbstractFactory;
import io.github.ndys.patto.patterns.chain_of_responsibility.ChainOfResponsibility;
import io.github.ndys.patto.patterns.factory_method.FactoryMethod;
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
                case "13" -> ChainOfResponsibility.show("Main Menu > Chain of Responsibility");

                case "0" -> {
                    System.out.println("Exiting...👋 Goodbye!");
                    return;
                }

                default -> {
                    System.out.println("⚠️ Invalid choice. Please try again.");
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


