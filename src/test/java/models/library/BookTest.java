package models.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book;
    @BeforeEach
    void setUp() {
        book = new Book("introduction to chemistry", "ugo c ugo", 541,
                "chemistry", "12ER4");
    }

    @Test
    void testBookFieldsAreCorrectlySet() {
        assertAll(() -> {
            assertEquals("introduction to chemistry", book.getName(), "should equal 'introduction to chemistry'");
            assertEquals("ugo c ugo", book.getAuthor());
            assertEquals(541, book.getPageCount());
            assertEquals("chemistry", book.getGenre());
            assertEquals("12ER4", book.getIsbn());
            assertNotEquals("", book.getName());
            assertNotEquals("introduction to Chemistry", book.getName());
        });
    }
}