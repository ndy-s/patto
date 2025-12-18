package io.github.ndys.patto.patterns.prototype.example1_document_clone;


public class Document implements Prototype<Document> {

    private String title;
    private String content;
    private String author;

    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @Override
    public Document clone() {
        return new Document(title, content, author);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return """
               Document:
               - Title   : %s
               - Author  : %s
               - Content : %s
               """.formatted(title, author, content);
    }

}

