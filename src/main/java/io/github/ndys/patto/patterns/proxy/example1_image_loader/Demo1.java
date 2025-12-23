package io.github.ndys.patto.patterns.proxy.example1_image_loader;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Proxy > Image Loader");

        Image image = new ImageProxy("photo.png");

        System.out.println("\nFirst display call:");
        image.display();

        System.out.println("\nSecond display call:");
        image.display();
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}

    }
    
}

