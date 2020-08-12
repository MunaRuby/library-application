// Book.java: A class to model a book.

package models.library;

import java.util.Objects;

public class Book {
    private String isbn;
    private String name;
    private String author;
    private int pageCount;
    private String genre;

    public Book(String name, String author, int pageCount, String genre, String isbn) {
        this.name = name;
        this.author = author;
        this.pageCount = pageCount;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
