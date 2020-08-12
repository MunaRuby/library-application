package utils;

import models.library.Book;
import models.library.BookData;
import models.library.BookRequest;
import models.library.Library;
import models.people.User;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    User student1 = new User("kenechukwu", "okafor", Role.SENIOR_STUDENT);
    User student2 = new User("omolade", "rajput", Role.JUNIOR_STUDENT);
    User student3 = new User("samuel", "ochuba", Role.JUNIOR_STUDENT);
    User teacher = new User("emmanuel", "chigere", Role.TEACHER);
    Book book = new Book("introduction to chemistry", "ugo c ugo", 541,
            "chemistry", "12ER4");
    Book book1 = new Book("mastering english", "titilola balogun", 230,
            "english", "1112EE1");
    PriorityQueue priorityQueue = new PriorityQueue(new Comparator<BookRequest>() {
        @Override
        public int compare(BookRequest o1, BookRequest o2) {
            if (o1.getBookName().equals(o2.getBookName())) {
                if (o1.getLender().getRole() == Role.TEACHER && o2.getLender().getRole() == Role.JUNIOR_STUDENT ) {
                    return 1;
                }

                if ((o1.getLender().getRole() == Role.JUNIOR_STUDENT ||
                        o1.getLender().getRole() == Role.SENIOR_STUDENT) && o2.getLender().getRole() == Role.TEACHER) {
                    return -1;
                }

                if (o1.getLender().getRole() == Role.JUNIOR_STUDENT &&
                        o2.getLender().getRole() == Role.SENIOR_STUDENT)
                    return -1;

                if (o1.getLender().getRole() == Role.SENIOR_STUDENT &&
                        o2.getLender().getRole() == Role.JUNIOR_STUDENT)
                    return 1;
                if (o1.getLender().getRole() == Role.LIBRARIAN &&
                        o2.getLender().getRole() == Role.TEACHER)
                    return 0;
            }
            return  0;
        }
    }.reversed());

    @Test
    void testingOfferImplementation1() {
        // Teacher, Seniors Come First Implementation
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        priorityQueue.offer(new BookRequest(student3, book.getName()));
        priorityQueue.offer(new BookRequest(student1, book.getName()));
        priorityQueue.offer(new BookRequest(teacher, book.getName()));

        BookRequest bk1 = priorityQueue.poll();
        BookRequest bk2 = priorityQueue.poll();
        BookRequest bk3 = priorityQueue.poll();

        assertAll(() -> {
            assertNotNull(bk1);
            assertNotNull(bk2);
            assertNotNull(bk3);
            assertEquals(Role.TEACHER, bk1.getLender().getRole());
            assertEquals(Role.SENIOR_STUDENT, bk2.getLender().getRole());
            assertEquals(Role.JUNIOR_STUDENT, bk3.getLender().getRole());
        });
    }

    @Test
    void testingOfferImplementation2() {
        // First Come - First Serve Implementation
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        priorityQueue.offer(new BookRequest(student1, book.getName()));
        priorityQueue.offer(new BookRequest(teacher, book.getName()));
        priorityQueue.offer(new BookRequest(student3, book.getName()));

        BookRequest bk1 = priorityQueue.poll();
        BookRequest bk2 = priorityQueue.poll();
        BookRequest bk3 = priorityQueue.poll();

        assertAll(() -> {
            assertNotNull(bk1);
            assertNotNull(bk2);
            assertNotNull(bk3);
            assertEquals(Role.JUNIOR_STUDENT, bk1.getLender().getRole());
            assertEquals(Role.SENIOR_STUDENT, bk2.getLender().getRole());
            assertEquals(Role.TEACHER, bk3.getLender().getRole());
        });
    }

    @Test
    void poll() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        BookRequest bk = priorityQueue.poll();
        assertNotNull(bk);
        assertEquals(Role.JUNIOR_STUDENT, bk.getLender().getRole());
    }

    @Test
    void peek() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        BookRequest bk = priorityQueue.peek();
        assertNotNull(bk);
        assertEquals(Role.JUNIOR_STUDENT, bk.getLender().getRole());
    }

    @Test
    void size() {
        PriorityQueue priorityQueue = new PriorityQueue();
        assertEquals(0, priorityQueue.size());
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        assertEquals(1, priorityQueue.size());
    }

    @Test
    void isEmpty() {
        PriorityQueue priorityQueue = new PriorityQueue();
        assertTrue(priorityQueue.isEmpty());
        priorityQueue.offer(new BookRequest(student2, book.getName()));
        assertFalse(priorityQueue.isEmpty());
    }
}