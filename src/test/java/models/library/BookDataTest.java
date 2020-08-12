package models.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDataTest {
    BookData bookdata;
    Book book;

    @BeforeEach
    void setUp() {
        book = new Book("introduction to chemistry", "ugo c ugo", 541,
                "chemistry", "12ER4");
        bookdata = new BookData();
        bookdata.setBook(book);
        bookdata.setBooksLeft(1);
        bookdata.setTotalNoOfBooks(5);
    }

    @Test
    void testBookdataFieldsAreCorrectlySet() {
        assertAll(() -> {
            assertEquals(book, bookdata.getBook(), "should equal book");
            assertEquals(1, bookdata.getBooksLeft());
            assertEquals(5, bookdata.getTotalNoOfBooks());
        });
    }

    @Test
    void testBookDataMethodsBehaveCorrectly() {
        assertAll(() -> {
            bookdata.setBooksLeft(10);
            bookdata.setTotalNoOfBooks(11);
            assertEquals(11, bookdata.getTotalNoOfBooks());
            assertEquals(10, bookdata.getBooksLeft());
            assertNotEquals(1, bookdata.getBooksLeft());
            assertNotEquals(5, bookdata.getTotalNoOfBooks());
        });
    }
}