package bookstore.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, author, isbn;
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category kategoria;

    public Book() {
    }

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public Book(@NotEmpty(message = "Book's title cannot be empty.") @Size(min = 1, max = 250) String title,
            @NotEmpty(message = "Book's author cannot be empty.") @Size(min = 1, max = 250) String author,
            @NotEmpty(message = "Book's ISBN cannot be empty.") @Size(min = 1, max = 250) String isbn,
            @Min(value = 0, message = "Publication year cannot be negative or null") int publicationYear,
            Category kategoria) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.kategoria = kategoria;
    }

    public Category getKategoria() {
        return kategoria;
    }

    public void setKategoria(Category kategoria) {
        this.kategoria = kategoria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
                + publicationYear + "]";
    }

}
