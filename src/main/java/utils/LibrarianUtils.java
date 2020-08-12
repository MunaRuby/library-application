// LibrarianUtils.java: A utility class with methods that simulate a librarians duties
package utils;

import models.library.Book;
import models.library.BookRequest;
import models.library.Library;
import models.people.User;

public class LibrarianUtils {
    public LibraryUtils libraryUtils = new LibraryUtils();
    public Library library;

    public LibrarianUtils(Library library) {
        this.library = library;
    }

    /**
     * addBook adds a book to the library
     * @param librarian
     * @param book
     * @throws NotAllowedException
     */
    public void addBook (User librarian, Book book) throws NotAllowedException {
        if (librarian.getRole() != Role.LIBRARIAN) throw new NotAllowedException("you do not have required permissions!");

        else libraryUtils.addBook(library.getBooks(), book);
    }

    /**
     * lendBook lends a book to a user. It returns true if
     * @param person
     * @param librarian
     * @param bookName
     * @return boolean
     * @throws NotAllowedException
     */
    public boolean lendBook (User person, User librarian,  String bookName) throws NotAllowedException {
        // Only a librarian can get access to libraryUtils
        if (librarian.getRole() != Role.LIBRARIAN) throw new NotAllowedException("you do not have required permissions!");
        BookRequest bookRequest = new BookRequest(person, bookName);
        boolean added = libraryUtils.addToBookRequestQueue(library.getRequestQueue(), bookRequest);

        // When we have 9 requests or more in the queue, begin processing these requests
        if (library.getRequestQueue().size() >= 10) {
            libraryUtils.lend(library.getRequestQueue(), library.getBooks(), library.getCurrentLenders(), library.getRequestHistory());
        }

        return added;
    }

    /**
     *
     * @param person
     * @param librarian
     * @param book
     * @return boolean
     * @throws NotAllowedException
     */
    public boolean retrieveBook (User person, User librarian, Book book) throws NotAllowedException {
        // Only a librarian can get access to libraryUtils
        if (librarian.getRole() != Role.LIBRARIAN) throw new NotAllowedException("you do not have required permissions!");
        Book returnedBook = libraryUtils.retrieveBook(person, book, library.getCurrentLenders(), library.getRequestHistory(), library.getBooks());
        return returnedBook != null;
    }
}
