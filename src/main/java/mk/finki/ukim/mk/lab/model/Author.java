package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;
@Data
public class Author {
    @Getter
    Long id;
    String name;
    String surname;
    String biography;

    public Author(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
