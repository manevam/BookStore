package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import lombok.Getter;

@Data
public class BookStore {

    private Long id;
    @Getter
    private String name;
    @Getter
    private String city;
    @Getter
    private String address;

    public BookStore(Long id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
