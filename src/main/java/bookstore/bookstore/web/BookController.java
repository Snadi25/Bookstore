package bookstore.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        log.info("getAllBooks...");
        model.addAttribute("kirjat", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/addBook")
    public String addBookForm(Model model) {
        model.addAttribute("kirja", new Book());
        return "addBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book) {
        log.info("CONTROLLER: Save book: " + book);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        log.info("Delete book which id = " + id);
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/editBook/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        log.info("Edit book which id = " + id);
        model.addAttribute("editBook", bookRepository.findById(id));
        return "editBook";
    }

    @PostMapping("/saveEditedBook")
    public String saveEditedBook(Book book) {
        log.info("CONTROLLER: Save edited the book " + book);
        bookRepository.save(book);
        return "redirect:/books";
    }

}
