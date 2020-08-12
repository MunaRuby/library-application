/**
 * LibraryUtils class is a utility class that helps with management of the Library model.
 * It provides methods with work directly with the library's fields
 */

package utils;

import models.library.Book;
import models.library.BookData;
import models.library.BookRequest;
import models.people.User;

import java.time.LocalDateTime;
import java.util.*;

public class LibraryUtils {
    /**
     * Adds a new book to the request queue
     * @param requestQueue
     * @param bookRequest
     * @return
     */
    public boolean addToBookRequestQueue(PriorityQueue requestQueue, BookRequest bookRequest) {
        return requestQueue.offer(bookRequest);
    }

    /**
     * adds a new book to a list of same books
     * @param book
     * @return Book
     */
    public Book addBook (Map<String, BookData> books, Book book) {
        BookData bookData = books.get(book.getName());

        // if bookData is null add a new bookData object
        if (bookData == null) {
            bookData = new BookData();
            bookData.setBook(book);
            // The librarian adds 3 copies of a new book
            bookData.setBooksLeft(bookData.getBooksLeft() + 2);
        }
        // increases the books left for that book
        bookData.setBooksLeft(bookData.getBooksLeft() + 1);
        // increases the total number of books for the particular book
        bookData.setTotalNoOfBooks(bookData.getTotalNoOfBooks() + 1);
        books.put(book.getName(), bookData);
        return book;
    }

    /**
     * Retrieve a single book from a list of books in the library, if the BookData for
     * that book doesn't exist, throw an exception
     * @param bookName
     * @return Book
     * @throws NotAllowedException
     */
    private Book retrieveBook (Map<String, BookData> books, String bookName) throws NotAllowedException {
        BookData bookData = books.get(bookName);

        if (bookData == null) throw new NotAllowedException("book does not exist in the library!");
        else if (bookData.getBooksLeft() < 1) throw new NotAllowedException();
        bookData.setBooksLeft(bookData.getBooksLeft() - 1);
        return bookData.getBook();
    }

    /**
     * Lend method is uses the request queue to end to the highest priority request in the queue
     * @return Book
     * @throws NotAllowedException
     */
    public boolean lend(PriorityQueue requestQueue, Map<String, BookData> books,
                        List<BookRequest> currentLenders, Set<BookRequest> requestHistory) throws NotAllowedException {
        boolean flag = false;

        while (!requestQueue.isEmpty()) {
            BookRequest request = requestQueue.poll();
            Book book = retrieveBook(books, request.getBookName());
            Set<Book> borrowedBooks = request.getLender().getListOfBorrowedBooks();

            // Throw a new exception if the user wants to borrow the same book he/she borrowed before
            if (borrowedBooks.contains(book)) throw new NotAllowedException("You cannot borrow another copy of this book!");
            borrowedBooks.add(book);
            flag = true;

            // Adds the current request object to the list of lenders.
            currentLenders.add(request);
            // Adds the current request object to the overall transaction history.
            requestHistory.add(request);
        }
        return flag;
    }

    /**
     * returnBook returns a book back to the library.
     * @param person
     * @param book
     * @return Book
     * @throws NotAllowedException if the lender returns a book other than that which was borrowed.
     */
    public Book retrieveBook(User person, Book book, List<BookRequest> currentLenders,
                             Set<BookRequest> requestHistory, Map<String, BookData> books) throws NotAllowedException {
        Iterator<BookRequest> requestIterator = currentLenders.iterator();
        Book returnedBook = null;
        for (BookRequest request : currentLenders) {
            if (request.getLender().equals(person)) {
                // If the lender borrowed a different book from the book returned throw an exception
                if (!request.getBookName().equals(book.getName())) throw new NotAllowedException("A different book was borrowed!");
                else {
                    // else add book back to list of books and remove lender from the lenders set
                    addBook(books, book);
                    person.getListOfBorrowedBooks().remove(book);
                    currentLenders.remove(request);
                    returnedBook = book;

                    // set the time the book was returned in the request
                    request.setTimeReturned(LocalDateTime.now());
                    setTimeBookWasReturned(request, requestHistory);
                    break;
                }
            }
        }
        return returnedBook;
    }

    private void setTimeBookWasReturned (BookRequest request, Set<BookRequest> requestHistory) {
        requestHistory.forEach(requestObj -> {
            if (requestObj.getLender().getId().equals(request.getLender().getId()) &&
                    requestObj.getBookName().equals(request.getBookName())) {
                requestObj.setTimeReturned(LocalDateTime.now());
            }
        });
    }
}
