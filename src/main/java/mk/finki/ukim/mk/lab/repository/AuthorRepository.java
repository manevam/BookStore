package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class AuthorRepository {
    List<Author> authors;

    public AuthorRepository() {
        authors = new ArrayList<>(5);
        authors.add(new Author(1L, "Dorian", "Gray"));
        authors.add(new Author(2L, "Voltaire", ""));
        authors.add(new Author(3L, "Ruth", "Rendell"));
    }

    public List<Author> findAll(){
        return  authors;
    }
    public Optional<Author> findById(Long id){
        Optional<Author> first = authors.stream().filter(a -> a.getId().equals(id)).findFirst();
        return first;
    }


}
