// NotAllowedException.java: Utility exception class for library application
package utils;

public class NotAllowedException extends Exception {
    public NotAllowedException() {
        this("Book Taken!");
    }

    public NotAllowedException(String message) {
        super(message);
    }
}
