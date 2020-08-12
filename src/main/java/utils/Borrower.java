package utils;

import models.library.Book;
import models.people.User;

public interface Borrower {
    <T extends User> boolean borrowBook(T person, T librarian, String bookName);
    <T extends User> boolean returnBook (T person, T librarian, Book book);
}
