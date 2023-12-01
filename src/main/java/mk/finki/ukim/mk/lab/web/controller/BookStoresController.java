package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/bookStores")
public class BookStoresController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public BookStoresController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksStoresPage(Model model) {
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "bookStores";
    }

    @GetMapping("/details/{id}")
    public String getDetailsBookStores(@PathVariable String id, Model model){
        Optional<BookStore> bookStore = bookStoreService.findBookStoreById(Long.parseLong(id));
        if(bookStore.isPresent()){
            model.addAttribute("bookStore", bookStore.get());
            return "detailsBookStore";
        }

        return "redirect:/bookStores?error=BookStoreNotFound";
    }
}
