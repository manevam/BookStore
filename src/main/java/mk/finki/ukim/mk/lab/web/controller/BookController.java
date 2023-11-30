package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.ui.Model;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        model.addAttribute("books", bookService.listBooks());
        return "listBooks";
    }

    @GetMapping("/delete/{isbn}")
    public String delete(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return "redirect:/books";
    }

    @GetMapping("/edit/{isbn}")
    public String edit(@PathVariable String isbn, Model model) {
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        if(book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("bookStores", bookStoreService.findAll());
            return "add-book";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @PostMapping("/editBook/{isbn}")
    public String editBook(@PathVariable String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        bookService.editBook(isbn,title,genre,year, bookStoreId);
        return "redirect:/books";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStoreId) {
        bookService.saveBook(title,genre,year,bookStoreId);
        return "redirect:/books";
    }

    @GetMapping("/add")
    public String saveMovie(Model model) {
        model.addAttribute("bookStores", bookStoreService.findAll());
        return "add-book";
    }





}
