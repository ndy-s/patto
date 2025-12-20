package io.github.ndys.patto.patterns.composite.example1_file_system;

import io.github.ndys.patto.patterns.composite.example1_file_system.composite.Folder;
import io.github.ndys.patto.patterns.composite.example1_file_system.leaf.FileItem;
import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Composite > File System");

        Folder root = new Folder("root");

        Folder documents = new Folder("documents");
        documents.add(new FileItem("resume.pdf", 120));
        documents.add(new FileItem("design.docs", 340));

        Folder images = new Folder("images");
        images.add(new FileItem("photo.png", 560));
        images.add(new FileItem("logo.svg", 80));

        root.add(documents);
        root.add(images);
        root.add(new FileItem("readme.txt", 30));

        root.print("");
        System.out.println();
        System.out.println("Total size: " + root.getSize() + " KB");

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

