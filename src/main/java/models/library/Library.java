// Library.java: A class for to model a library and contains all necessary library fields

package models.library;

import utils.PriorityQueue;
import utils.Role;

import java.util.*;

public class Library {
    // All books in the library
//    private Map<String, Map<Book, Integer>> books = new HashMap<>();
    private Map<String, BookData> books = new HashMap<>();

    // A queue of requests to borrow books.
    /*
        The Comparator provided compares book request based on the role lender of the book.
        All staff objects, a librarian and a teacher would have equal priority over student
        objects. Where senior students would have priority over junior students
     */
    private PriorityQueue requestQueue = new PriorityQueue(new Comparator<BookRequest>() {
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

    // The current book lenders, i.e persons who have library books in their possesion
    private List<BookRequest> currentLenders = new ArrayList<>();

    // A history of all requests (lend and return) in the library;
    private Set<BookRequest> requestHistory = new HashSet<>();

    public Library () {}
    public Library(boolean priority) {
        if (!priority)
            this.setRequestQueue(new PriorityQueue());
    }

    public Map<String, BookData> getBooks() {
        return books;
    }

    public PriorityQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(PriorityQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public List<BookRequest> getCurrentLenders() {
        return currentLenders;
    }

    public Set<BookRequest> getRequestHistory() {
        return requestHistory;
    }
}
