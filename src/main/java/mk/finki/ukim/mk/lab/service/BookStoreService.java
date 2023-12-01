package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {

    public List<BookStore> findAll();
    public Optional<BookStore> findBookStoreById(Long id);
}
