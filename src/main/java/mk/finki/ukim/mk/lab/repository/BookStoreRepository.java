package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.ArrayList;
import java.util.List;

public class BookStoreRepository {

    List<BookStore> bookStores;

    public BookStoreRepository() {
        this.bookStores = new ArrayList<>();
        bookStores.add(new BookStore(0L, "Literatura", "Skopje", "Adresa 1"));
        bookStores.add(new BookStore(1L, "Prosvetno Delo", "Skopje", "Adresa 2"));
        bookStores.add(new BookStore(2L, "Albi", "Skopje", "Adresa 3"));
        bookStores.add(new BookStore(3L, "Random Knizara", "Skopje", "Adresa 4"));
        bookStores.add(new BookStore(0L, "Kul Knizara", "Skopje", "Adresa 5"));
    }

    public List<BookStore> findAll(){
        return bookStores;
    }
}
