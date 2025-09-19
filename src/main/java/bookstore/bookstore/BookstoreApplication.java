package bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			Category category1 = new Category("Fantasy");
			Category category2 = new Category("Sci-Fi");

			Book book1 = new Book("A Farewell to Arms", "Ernest Hemingway", "1232323-21", 1929);
			Book book2 = new Book("Animal Farm", "George Orwell", "2212343-5", 1945);

			categoryRepository.save(category1);
			categoryRepository.save(category2);
			bookRepository.save(book1);
			bookRepository.save(book2);

			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
