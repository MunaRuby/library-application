// UserUtils.java: A utility class for handling user models

package utils;

import models.library.Book;
import models.people.User;

public class UserUtils implements Borrower{
    LibrarianUtils librarianUtils;

    public UserUtils(LibrarianUtils librarianUtils) {
        this.librarianUtils = librarianUtils;
    }

    /**
     * borrowBook calls is used to borrow a book from the library
     * @param lender
     * @param librarian
     * @param bookName
     */
    public boolean borrowBook(User lender, User librarian, String bookName) {
        boolean borrowedSuccessfully = false;
        try {
            // librarian is in charge of lending books
            borrowedSuccessfully = librarianUtils.lendBook(lender, librarian, bookName);
        } catch (NotAllowedException e) {
            System.out.println(e.getMessage());
        }

        return borrowedSuccessfully;
    }

    /**
     * returnBook is used to return a book back to the library, if the book
     * cannot be returned, it throws a NotAllowedException.
     * @param lender
     * @param librarian
     * @param book
     */
    public boolean returnBook(User lender, User librarian, Book book) {
        boolean returnedSuccessfuly = false;
        try {
            // librarian is in charge of retrieving books
            returnedSuccessfuly = librarianUtils.retrieveBook(lender, librarian, book);
        } catch (NotAllowedException e) {
            System.out.println(e.getMessage());
        }

        // remove the book from the users list of borrowed books if the
        // book was successfully returned
        if (returnedSuccessfuly) lender.getListOfBorrowedBooks().remove(book);
        return returnedSuccessfuly;
    }
}
