package utils;

import models.library.Book;
import models.library.BookRequest;
import models.library.Library;
import models.people.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUtilsTest {
    LibrarianUtils librarianUtils = new LibrarianUtils(new Library(), 1);
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
    void addToBookRequestQueue() {
        BookRequest bookRequest = new BookRequest(user, "introduction  to chemistry");
        libraryUtils.addToBookRequestQueue(librarianUtils.library.getRequestQueue(), bookRequest);

        assertEquals(1, librarianUtils.library.getRequestQueue().size());
    }

    @Test
    void addBook() {
        libraryUtils.addBook(librarianUtils.library.getBooks(), book);

        assertEquals(1, librarianUtils.library.getBooks().size());
        assertNotNull(librarianUtils.library.getBooks().get(book.getName()));
        assertEquals("introduction to chemistry", librarianUtils.library.getBooks().get(book.getName()).getBook().getName());
    }

    @Test
    void lend() throws NotAllowedException {
        userUtils.borrowBook(user, librarian, "introduction to chemistry");
        assertAll(() -> {
            assertFalse(libraryUtils.lend(librarianUtils.library.getRequestQueue(), librarianUtils.library.getBooks(), librarianUtils.library.getCurrentLenders(),
                    librarianUtils.library.getRequestHistory()), "should return false");
        });
        libraryUtils.addBook(librarianUtils.library.getBooks(), book);
        libraryUtils.addToBookRequestQueue(librarianUtils.library.getRequestQueue(), new BookRequest(user, "introduction to chemistry"));
        assertTrue(libraryUtils.lend(librarianUtils.library.getRequestQueue(), librarianUtils.library.getBooks(), librarianUtils.library.getCurrentLenders(),
                librarianUtils.library.getRequestHistory()), "should return true");


    }

    @Test
    void retrieveBook() throws NotAllowedException {
        assertNull(libraryUtils.retrieveBook(user, book, librarianUtils.library.getCurrentLenders(), librarianUtils.library.getRequestHistory(),
                librarianUtils.library.getBooks()));

        libraryUtils.addBook(librarianUtils.library.getBooks(), book);
        userUtils.borrowBook(user, librarian, "introduction to chemistry");
        assertNotNull(libraryUtils.retrieveBook(user, book, librarianUtils.library.getCurrentLenders(), librarianUtils.library.getRequestHistory(),
                librarianUtils.library.getBooks()));
    }
}