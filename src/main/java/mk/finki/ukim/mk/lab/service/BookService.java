package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookService{
    List<Book> listBooks();
    Optional<Author> addAuthorToBook(Long authorId, String isbn);
    Optional<Book> findBookByIsbn(String isbn);

    //vtora lab
    void deleteBook(String isbn);
    Optional<Book> editBook(String isbn, String title, String genre, Integer year, Long bookStoreId);
    Optional<Book> saveBook(String title, String genre, Integer year, Long bookStoreId);
    //for BONUS points
    void addMultipleAuthorsToBook(List<String> authorIds, String isbn);

}