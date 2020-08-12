package utils;

import models.library.Book;
import models.library.Library;
import models.people.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUtilsTest {
    LibrarianUtils librarianUtils = new LibrarianUtils(new Library());
    UserUtils userUtils;
    User user;
    User librarian;
    Book book;

    @BeforeEach
    void setUp() {
        userUtils = new UserUtils(librarianUtils);
        user = new User("Odiwa", "obianuju", Role.JUNIOR_STUDENT);
        librarian = new User("lesley", "okoduwa", Role.LIBRARIAN);
        book = new Book("introduction to chemistry", "ugo c ugo", 541,
                "chemistry", "12ER4");
        userUtils.borrowBook(user, librarian, "introduction to chemistry");
    }

    @Test
    void borrowBook() {

        boolean successfullyBorrowed = userUtils.borrowBook(user, librarian, "introduction to chemistry");
        userUtils.borrowBook(user, librarian, "mastering english");
        assertAll(() -> {
            assertEquals(3, librarianUtils.library.getRequestQueue().size());
            assertEquals(false, librarianUtils.library.getRequestQueue().isEmpty());
            assertEquals(true, successfullyBorrowed);
            assertEquals(1, user.getListOfBorrowedBooks().size());
        });
    }

    @Test
    void returnBook() {

    }
}