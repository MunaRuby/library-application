/*
 User.java: User is a concrete class that extends abstract class Person and
    provides a listOfBorrowedBooks field
 */

package models.people;

import models.library.Book;
import utils.Role;
import java.util.*;

public class User extends Person {
    Set<Book> listOfBorrowedBooks = new HashSet<>();

    public User(String firstName, String lastName, Role role) {
        super(firstName, lastName, role);
    }

    public Set<Book> getListOfBorrowedBooks() {
        return listOfBorrowedBooks;
    }
}
