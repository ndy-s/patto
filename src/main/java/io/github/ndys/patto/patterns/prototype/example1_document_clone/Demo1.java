package io.github.ndys.patto.patterns.prototype.example1_document_clone;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {

    public static void run() {
        TerminalPrinter.printHeader("Prototype > Document Cloning");

        DocumentRegistry registry = new DocumentRegistry();

        registry.register("report", new Document("Monthly Report", "Sales increased by 20%", "System"));

        registry.register("letter", new Document("Formal Letter", "Dear Sir/Madam", "Admin"));

        Document doc1 = registry.create("report");
        doc1.setTitle("Monthly Report - Copy");

        Document doc2 = registry.create("letter");
        doc2.setTitle("Job Application Letter");

        System.out.println("Cloned Documents:");
        System.out.println(doc1);
        System.out.println(doc2);

        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
    
}

