package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="book-details-servlet", urlPatterns = "/bookDetails")
public class BookDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final AuthorService authorService;

    public BookDetailsServlet(SpringTemplateEngine springTemplateEngine, BookService bookService, AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context =  new WebContext(webExchange);

        String isbn = (String) req.getSession().getAttribute("isbn");
        //context.setVariable("bookObj", bookService.findBookByIsbn(isbn));

        String authorId = (String)req.getSession().getAttribute("authorId");
        context.setVariable("authorObj", authorService.findById(Long.getLong(authorId)));
        springTemplateEngine.process(
                "bookDetails.html",
                context,
                resp.getWriter()
        );

    }
}
