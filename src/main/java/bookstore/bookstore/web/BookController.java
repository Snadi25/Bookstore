package bookstore.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.CategoryRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
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
        model.addAttribute(("categories"), categoryRepository.findAll());
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
        model.addAttribute("categories", categoryRepository.findAll());
        return "editBookWithValidation";
    }

    @PostMapping("/saveEditedBook")
    public String saveEditedBook(@Valid @ModelAttribute("editBook") Book book,
            BindingResult bindingResult, Model model) {
        log.info("CONTROLLER: Save edited the book - check validation of book:  " + book);
        if (bindingResult.hasErrors()) {
            log.error("some validation error happened, book: " + book);
            model.addAttribute("editBook", book);

            return "editBookWithValidation";
        }
        log.info("Save book: " + book);
        bookRepository.save(book);
        return "redirect:/books";
    }

}
