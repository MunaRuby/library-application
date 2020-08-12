import models.library.Book;
import models.library.BookRequest;
import models.library.Library;
import models.people.User;
import utils.LibrarianUtils;
import utils.NotAllowedException;
import utils.Role;
import utils.UserUtils;

public class Main {
    public static void main(String[] args) {

        // Initialize models
        User librarian = new User("lesley", "okoduwa", Role.LIBRARIAN);
        Library library = new Library();
        LibrarianUtils librarianUtils = new LibrarianUtils(library);
        UserUtils userUtils = new UserUtils(librarianUtils);

        User student1 = new User("kenechukwu", "okafor", Role.SENIOR_STUDENT);
        User student2 = new User("Odiwa", "obianuju", Role.JUNIOR_STUDENT);
        User student3 = new User("daniel", "otulagun", Role.JUNIOR_STUDENT);
        User student4 = new User("omolade", "rajput", Role.JUNIOR_STUDENT);
        User student5 = new User("samuel", "ochuba", Role.JUNIOR_STUDENT);

        User teacher = new User("emmanuel", "chigere", Role.TEACHER);

        Book book = new Book("introduction to chemistry", "ugo c ugo", 541,
                "chemistry", "12ER4");

        Book book1 = new Book("mastering english", "titilola balogun", 230,
                "english", "1112EE1");

        // The librarian adds a book to the library
        try {
            librarianUtils.addBook(librarian, book);
            librarianUtils.addBook(librarian, book);
            librarianUtils.addBook(librarian, book);
            librarianUtils.addBook(librarian, book1);
            librarianUtils.addBook(librarian, book1);
            librarianUtils.addBook(librarian, book1);
        } catch (NotAllowedException e) {
            e.printStackTrace();
        }

        // users try to borrow books
        userUtils.borrowBook(student1, librarian, book.getName());
        userUtils.borrowBook(student1, librarian, book1.getName());
        userUtils.borrowBook(student4, librarian, book.getName());
        userUtils.borrowBook(student3, librarian, book.getName());
        userUtils.borrowBook(student2, librarian, book1.getName());
        userUtils.borrowBook(teacher, librarian, book.getName());
        userUtils.borrowBook(student5, librarian, book.getName());
        userUtils.borrowBook(student4, librarian, book1.getName());
        userUtils.borrowBook(student1, librarian, book1.getName());
        System.out.println();

        // The priority queue is obtained manually and is used to check the order
        // in which the requests were sorted

//        System.out.println(library.getRequestQueue());
//        BookRequest rq1 = library.getRequestQueue().poll();
//        BookRequest rq2 = library.getRequestQueue().poll();
//        BookRequest rq3 = library.getRequestQueue().poll();
//        BookRequest rq4 = library.getRequestQueue().poll();
//        BookRequest rq5 = library.getRequestQueue().poll();
//        BookRequest rq6 = library.getRequestQueue().poll();
//        BookRequest rq7 = library.getRequestQueue().poll();
//        BookRequest rq8 = library.getRequestQueue().poll();
//        BookRequest rq9 = library.getRequestQueue().poll();
//
//        System.out.println(rq1.getLender().getRole() + " " + rq1.getBookName());
//        System.out.println(rq2.getLender().getRole() + " " + rq2.getBookName());
//        System.out.println(rq3.getLender().getRole() + " " + rq3.getBookName());
//        System.out.println(rq4.getLender().getRole() + " " + rq4.getBookName());
//        System.out.println(rq5.getLender().getRole() + " " + rq5.getBookName());
//        System.out.println(rq6.getLender().getRole() + " " + rq6.getBookName());
//        System.out.println(rq7.getLender().getRole() + " " + rq7.getBookName());
//        System.out.println(rq8.getLender().getRole() + " " + rq8.getBookName());
//        System.out.println(rq9.getLender().getRole() + " " + rq9.getBookName());


        // we check if the users requests were processed
//        System.out.println(student4.getListOfBorrowedBooks());
//        System.out.println(student3.getListOfBorrowedBooks());
//        System.out.println(student2.getListOfBorrowedBooks());
//        System.out.println(student1.getListOfBorrowedBooks());
//        System.out.println(teacher.getListOfBorrowedBooks());
//
        // student1 returns a book
//        userUtils.returnBook(student1, librarian, book);
//
        // we check if the book was returned
//        System.out.println(student4.getListOfBorrowedBooks());
//        System.out.println(student3.getListOfBorrowedBooks());
//        System.out.println(student2.getListOfBorrowedBooks());
//        System.out.println(student1.getListOfBorrowedBooks());
//        System.out.println(teacher.getListOfBorrowedBooks());
    }
}
