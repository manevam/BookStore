package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.bootstraph.DataHolder;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Override
    public List<BookStore> findAll() {
        return DataHolder.bookStores;
    }

    @Override
    public Optional<BookStore> findBookStoreById(Long id) {
        return DataHolder.bookStores.stream().filter(b -> b.getId().equals(id)).findFirst();
    }

}
