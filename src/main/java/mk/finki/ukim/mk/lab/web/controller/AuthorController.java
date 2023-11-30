package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookStoreService bookStoreService;

    public AuthorController(BookService bookService, AuthorService authorService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam String selectedBook,Model model) {
        Optional<Book> book = bookService.findBookByIsbn(selectedBook);
        if(book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.listAuthors());
                return "authorList";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @PostMapping("/addAuthor/{isbn}")
    public String getAuthors( @PathVariable String isbn,
                              @RequestParam Long authorId,
                              Model model) {
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        Optional<Author> author = authorService.findById(authorId);
        if(book.isPresent() && author.isPresent()) {
            bookService.addAuthorToBook(authorId,isbn);
            model.addAttribute("authors", book.get().getAuthors());
            model.addAttribute("bookObj", book.get());
            return "bookDetails";
        }
        return "redirect:/books?error=BookNotFound";
    }

    @GetMapping("/multiple/{isbn}")
    public String getMultipleAuthors(@PathVariable String isbn, Model model){
        Optional<Book> book = bookService.findBookByIsbn(isbn);
        if(book.isPresent()) {
            model.addAttribute("book", book.get());
            model.addAttribute("authors", authorService.listAuthors());
            return "multipleAuthorList";
        }
        return "redirect:/authors?error";
    }


}
