package mk.finki.ukim.mk.lab.bootstraph;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = null;
    public static List<Author> authors = null;
    public static List<BookStore> bookStores = null;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
        bookStores = new ArrayList<>();

        bookStores.add(new BookStore(0L, "Literatura", "Skopje", "Adresa 1"));
        bookStores.add(new BookStore(1L, "Prosvetno Delo", "Skopje", "Adresa 2"));
        bookStores.add(new BookStore(2L, "Albi", "Skopje", "Adresa 3"));
        bookStores.add(new BookStore(3L, "Random Knizara", "Skopje", "Adresa 4"));
        bookStores.add(new BookStore(0L, "Kul Knizara", "Skopje", "Adresa 5"));

        books.add(new Book("Murder in Midsommar", "mystery", 1999, bookStores.get(0)));
        books.add(new Book("The Picture of Dorian Grey", "fantasy", 1899, bookStores.get(1)));
        books.add(new Book("Candide", "philosophy", 1789, bookStores.get(2)));

        authors.add(new Author(0L, "Dorian", "Gray"));
        authors.add(new Author(1L, "Voltaire", ""));
        authors.add(new Author(2L, "Ruth", "Rendell"));


    }
}
