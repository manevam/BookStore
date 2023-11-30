package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/multipleAuthors")
public class MultipleAuthorsController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public MultipleAuthorsController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/{isbn}")
    public String getMultipleAuthorsPage(@PathVariable String isbn, Model model){
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        if(book.isPresent()){
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.listAuthors());
            return "multipleAuthorList";
        }

        return "redirect:/multipleAuthors?error";
    }

    @PostMapping("/add/{isbn}")
    public String addMultipleAuthors(@PathVariable String isbn,
                                     @RequestParam List<String> authorId,
                                     Model model){
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        if(book.isPresent()){
            bookService.addMultipleAuthorsToBook(authorId, isbn);
            model.addAttribute("bookObj", book.get());
            model.addAttribute("authors", book.get().getAuthors());
            return "bookDetails";
        }

        return "redirect:/multipleAuthors?error";
    }
}
