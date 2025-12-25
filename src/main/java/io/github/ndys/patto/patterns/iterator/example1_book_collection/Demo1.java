package io.github.ndys.patto.patterns.iterator.example1_book_collection;

import io.github.ndys.patto.ui.TerminalPrinter;

public class Demo1 {
    
    public static void run() {
        TerminalPrinter.printHeader("Iterator > Book Collection");

        BookCollection collection = new BookCollection();
        collection.addBook(new Book("Design Patterns"));
        collection.addBook(new Book("Clean Code"));
        collection.addBook(new Book("Refactoring"));

        BookIterator iterator = collection.iterator();

        System.out.println("Iterating over book collection:");
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("- " + book.getTitle());
        }
        
        System.out.println("\nPress Enter to return...");
        try {
            System.in.read();
        } catch (Exception ignored) {}
    }
}

