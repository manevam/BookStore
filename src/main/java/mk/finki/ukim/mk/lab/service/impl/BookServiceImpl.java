package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.bootstraph.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.exceptions.BookStoreNotFoundException;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Author> addAuthorToBook(Long authorId, String isbn){
        Optional<Author> a = DataHolder.authors.stream().filter(au -> au.getId().equals(authorId)).findFirst();
        Optional<Book> b = bookRepository.findByIsbn(isbn);
        if(a.isPresent() && b.isPresent()){
            bookRepository.addAuthorToBook(a.get(),b.get());
        }
        return a;
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public Optional<Book> editBook(String isbn, String title, String genre, Integer year, Long bookStoreId) {
        BookStore bookStore1 = DataHolder.bookStores.stream()
                .filter(b -> b.getId().equals(bookStoreId))
                .findFirst()
                .orElseThrow(() -> new BookStoreNotFoundException(bookStoreId));
        return bookRepository.editBook(isbn, title, genre, year, bookStore1);
    }

    @Override
    public Optional<Book> saveBook(String title, String genre, Integer year, Long bookStoreId) {
        BookStore bookStore1 = DataHolder.bookStores.stream()
                .filter(b -> b.getId().equals(bookStoreId))
                .findFirst()
                .orElseThrow(() -> new BookStoreNotFoundException(bookStoreId));
        return bookRepository.saveBook(title,genre,year, bookStore1);
    }

    //for Bonus points
    @Override
    public void addMultipleAuthorsToBook(List<String> authorIds, String isbn) {
        for (String id : authorIds) {
            addAuthorToBook(Long.valueOf(id), isbn);
        }
    }
}
