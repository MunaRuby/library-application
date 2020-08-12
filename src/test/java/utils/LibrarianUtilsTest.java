package utils;

import models.library.Book;
import models.library.Library;
import models.people.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianUtilsTest {
    LibrarianUtils librarianUtils = new LibrarianUtils(new Library(), 5);
    LibraryUtils libraryUtils = new LibraryUtils();
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
    }

    @Test
    void addBook() throws NotAllowedException {
        librarianUtils.addBook(librarian, book);

        assertNotNull(librarianUtils.library.getBooks().get(book.getName()));
        assertEquals(1, librarianUtils.library.getBooks().size());
    }

    @Test
    void lendBook() throws NotAllowedException {
        librarianUtils.lendBook(user, librarian, "introduction to chemistry");
        System.out.println(librarianUtils.library.getRequestQueue());
        assertEquals(1, librarianUtils.library.getRequestQueue().size());
        assertNotNull(librarianUtils.library.getRequestQueue().poll());
    }

    @Test
    void retrieveBook() throws NotAllowedException {
        librarianUtils = new LibrarianUtils(new Library(), 1);
        librarianUtils.addBook(librarian, book);
        librarianUtils.lendBook(user, librarian, "introduction to chemistry");
        assertEquals(1, user.getListOfBorrowedBooks().size());

        boolean result =  librarianUtils.retrieveBook(user, librarian, book);
        assertTrue(result);
        assertEquals(0, user.getListOfBorrowedBooks().size());
    }
}