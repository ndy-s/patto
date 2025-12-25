package io.github.ndys.patto.patterns.pattern_name.example1_context;

import java.util.Scanner;
import io.github.ndys.patto.utils.MenuUtils;

public class Demo1 {

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        MenuUtils.printHeader("Template Pattern");

        System.out.println("--- Example Demo Template ---");
        System.out.println("Replace this section with your pattern demo logic.");

        // Example: pseudo-structure for handler chain (optional)
        // HandlerOrInterface handlerA = new HandlerA();
        // HandlerOrInterface handlerB = new HandlerB();
        // handlerA.setNext(handlerB);
        // Request request = new Request("Sample Request");
        // handlerA.handle(request);

        System.out.println("\nDemo complete. Press Enter to return to the menu...");
        scanner.nextLine();
    }
}

