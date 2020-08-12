package models.library;

import models.people.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Role;

import static org.junit.jupiter.api.Assertions.*;

class BookRequestTest {
    Book book;
    User user;
    BookRequest bookRequest;
    @BeforeEach
    void setUp() {
        user = new User("kenechukwu", "okafor", Role.SENIOR_STUDENT);
        book = new Book("introduction to chemistry", "ugo c ugo", 541,
                "chemistry", "12ER4");

        bookRequest = new BookRequest(user, "introduction to chemistry");
    }

    @Test
    void testBookRequestFieldsAreSetCorrectly() {
        assertAll(() -> {
            assertNotNull(bookRequest);
            assertNotNull(bookRequest.getLender());
            assertEquals(user, bookRequest.getLender());
            assertEquals("kenechukwu", bookRequest.getLender().getFirstName(), "should equal kenechukwu");
            assertEquals("introduction to chemistry", bookRequest.getBookName());
        });
    }
}