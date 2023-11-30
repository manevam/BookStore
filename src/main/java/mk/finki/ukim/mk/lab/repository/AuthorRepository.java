package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstraph.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static mk.finki.ukim.mk.lab.bootstraph.DataHolder.authors;

@Repository
public class AuthorRepository {


    public List<Author> findAll(){
         return DataHolder.authors;
    }

    public Optional<Author> findById(Long id){
        Optional<Author> first = DataHolder.authors.stream().filter(a -> a.getId().equals(id)).findFirst();
        return first;
    }

}
