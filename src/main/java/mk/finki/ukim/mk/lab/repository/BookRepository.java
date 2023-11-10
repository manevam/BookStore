package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books;

    public BookRepository() {
        books = new ArrayList<>(5);
        books.add(new Book("1", "Murder in Midsommar", "mystery", 1999));
        books.add(new Book("2", "The Picture of Dorian Grey", "fantasy", 1899));
        books.add(new Book("3", "Candide", "philosophy", 1789));
    }

    public List<Book> findAll(){
        return books;
    }
    public Book findByIsbn(String isbn){
        Book book = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
        return book;
    }
    public Author addAuthorToBook(Author author, Book book){
        Book neededBook = findByIsbn(book.getIsbn());
        neededBook.addAuthorToBook(author);
        //zoshto e potrebno nekakov return?
        return author;
    }
}
