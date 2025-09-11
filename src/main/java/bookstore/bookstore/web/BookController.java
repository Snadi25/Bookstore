package bookstore.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("kirjat", bookRepository.findAll());
        return "booklist";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("kirja", new Book());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("kirja") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long bookId, Model model) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Wrong ID: " + bookId));
        model.addAttribute("kirja", book);
        return "editbook";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("kirja") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

}
