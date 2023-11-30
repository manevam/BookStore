package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static mk.finki.ukim.mk.lab.bootstraph.DataHolder.books;

@Repository
public class BookRepository {

    public List<Book> findAll(){
        return books;
    }

    public Optional<Book> findByIsbn(String isbn){
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
    }
    public void addAuthorToBook(Author author, Book book) {
        Optional<Book> b = findByIsbn(book.getIsbn());
        b.ifPresent(value -> value.getAuthors().add(author));
    }

    public void deleteByIsbn(String isbn){
        books.removeIf(b ->b.getIsbn().equals(isbn));
    }

    public Optional<Book> editBook(String isbn, String title, String genre, Integer year, BookStore bookStore) {
        if( bookStore == null)
            throw new IllegalArgumentException();

        Optional<Book> b = findByIsbn(isbn);
        if(b.isPresent()){
            b.get().setTitle(title);
            b.get().setGenre(genre);
            b.get().setYear(year);
            b.get().setBookStore(bookStore);
        }
        return b;
    }

    public Optional<Book> saveBook(String title, String genre, Integer year, BookStore bookStore){
        if( bookStore == null)
            throw new IllegalArgumentException();
        Book book = new Book(title,genre,year, bookStore);
        books.add(book);
        return Optional.of(book);
    }

}
