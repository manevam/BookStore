package mk.finki.ukim.mk.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class BookStoreLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreLabApplication.class, args);
	}

}
