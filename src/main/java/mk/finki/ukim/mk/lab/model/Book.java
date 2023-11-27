package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class Book  implements Serializable {
    @Getter
    String isbn;
    String title;
    String genre;
    int year;
    @Getter
    List<Author> authors = new ArrayList<>();
    Long id;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.id = (long) (Math.random() * 1000);
    }



    public void addAuthorToBook(Author author){
        authors.add(author);
    }
}
