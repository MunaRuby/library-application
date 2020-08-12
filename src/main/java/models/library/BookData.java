// BookData.java: A class for book management

package models.library;

public class BookData {
    // The total number of copies of this book the library ought to have
    private int totalNoOfBooks;

    // The books left after books have been borrowed. if totalNoOfBooks is equal to booksleft
    // then no book has been borrowed
    private int booksLeft;
    private Book book;

    public int getTotalNoOfBooks() {
        return totalNoOfBooks;
    }

    public void setTotalNoOfBooks(int totalNoOfBooks) {
        this.totalNoOfBooks = totalNoOfBooks;
    }

    public int getBooksLeft() {
        return booksLeft;
    }

    public void setBooksLeft(int booksLeft) {
        this.booksLeft = booksLeft;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
