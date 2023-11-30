package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class Book  implements Serializable {
    @Getter
    String isbn;
    @Setter
    String title;
    @Setter
    String genre;
    @Setter
    int year;
    @Getter
    List<Author> authors = new ArrayList<>();
    Long id;
    @Setter
    private BookStore bookStore;

    public Book(String title, String genre, int year, BookStore bookStore) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.id =  (long) (Math.random() * 1000) ;
        this.isbn = String.valueOf(this.id);
        this.bookStore = bookStore;
    }

}
