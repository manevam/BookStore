package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static mk.finki.ukim.mk.lab.bootstraph.DataHolder.bookStores;

@Repository
public class BookStoreRepository {
    public List<BookStore> findAll(){
        return bookStores;
    }
}
