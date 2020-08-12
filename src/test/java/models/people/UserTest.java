package models.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Role;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    void setUp() {
        user = new User("kenechukwu", "okafor", Role.SENIOR_STUDENT);
    }

    @Test
    void testUserFieldsAreInitializedCorrectly() {
        assertAll(() -> {
            assertNotNull(user);
            assertEquals("kenechukwu", user.getFirstName());
            assertEquals(Role.SENIOR_STUDENT, user.getRole());
        });
    }
}