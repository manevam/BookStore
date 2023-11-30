package mk.finki.ukim.mk.lab.model.exceptions;

public class BookStoreNotFoundException extends RuntimeException {
    public BookStoreNotFoundException(Long BookStoreId) {
        super(String.format("Book Store with id %d doesn't exist", BookStoreId));
    }
}
