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

@WebServlet(name="author-servlet", urlPatterns = "/author")
public class АuthorServlet  extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;

    private final BookService bookService;

    public АuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
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
                "authorList.html",
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

        String field = req.getParameter("button");

        if(field.equals("Add Multiple Authors")){
            resp.sendRedirect("/multiple/author");
        }
        else {

            String isbn = (String) req.getSession().getAttribute("isbn");
            String authorId = req.getParameter("authorId");

            Author a = bookService.addAuthorToBook(Long.valueOf(authorId), isbn);
            req.getSession().setAttribute("authorId", authorId);
            resp.sendRedirect("/bookDetails");
        }
    }
}
