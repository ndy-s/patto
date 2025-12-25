package io.github.ndys.patto.patterns.iterator.example1_book_collection;

import java.util.List;

public class BookIterator {
    private final List<Book> books;
    private int position = 0;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    public boolean hasNext() {
        return position < books.size();
    }

    public Book next() {
        return books.get(position++);
    }
    
}

