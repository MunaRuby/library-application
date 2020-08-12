/*
    BookRequest.java: A class that model a request to  borrow a book.
 */

package models.library;
import models.people.User;

import java.time.LocalDateTime;

public class BookRequest {
    private User lender;
    private String bookName;

    // time borrowed is set automatically when the request is made
    private LocalDateTime timeBorrowed;

    // time returned is set automatically when the book is returned
    private LocalDateTime timeReturned;


    public BookRequest(User lender, String bookName) {
        this.lender = lender;
        this.bookName = bookName;
        this.timeBorrowed = LocalDateTime.now();
    }

    public User getLender() {
        return lender;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDateTime getTimeBorrowed() {
        return timeBorrowed;
    }

    public LocalDateTime getTimeReturned() {
        return timeReturned;
    }

    public void setTimeReturned(LocalDateTime timeReturned) {
        this.timeReturned = timeReturned;
    }
}
