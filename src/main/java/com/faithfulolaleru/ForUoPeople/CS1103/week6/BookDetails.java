package com.faithfulolaleru.ForUoPeople.CS1103.week6;

public class BookDetails {

    private int pageCount;
    private String genre;
    private String isbn;

    public BookDetails(int pageCount, String genre, String isbn) {
        this.pageCount = pageCount;
        this.genre = genre;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book [Pages: " + pageCount + ", Genre: " + genre + ", ISBN: " + isbn + "]";
    }
}
