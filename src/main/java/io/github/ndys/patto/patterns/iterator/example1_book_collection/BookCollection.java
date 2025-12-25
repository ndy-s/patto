package io.github.ndys.patto.patterns.iterator.example1_book_collection;

import java.util.ArrayList;
import java.util.List;

public class BookCollection {
    public final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public BookIterator iterator() {
        return new BookIterator(books);
    }

}

