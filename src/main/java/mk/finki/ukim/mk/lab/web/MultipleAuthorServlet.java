package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

//for Bonus points
@WebServlet(name="multiple-author-servlet", urlPatterns = "/multiple/author")
public class MultipleAuthorServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;

    private final BookService bookService;

    public MultipleAuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context =  new WebContext(webExchange);

        context.setVariable("authors", authorService.listAuthors());
        springTemplateEngine.process(
                "multipleAuthorList.html",
                context,
                resp.getWriter()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String isbn = (String) req.getSession().getAttribute("isbn");
        //List<String> authorId = req.getParameter("authorId");
        List<String> authorsSelected = List.of(req.getParameterValues("authorId"));
        //bookService.addMultipleAuthorsToBook(authorsSelected,isbn);

        req.getSession().setAttribute("authorsAdded", authorsSelected);
        resp.sendRedirect("/bookDetails");

    }
}
