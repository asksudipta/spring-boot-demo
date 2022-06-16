package se.lexicon.springbootdemo.entity;


import javax.persistence.*;
import java.util.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    private String isbn;
    private String title;
    private int maxLoanDays;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Author> authors;

    public void authorsBook(Author authorName) {
        //check value is null or not.
        if (authorName == null) throw new IllegalArgumentException("Author name is null");

        //Check the parameter and convert set to arraylist.
        if (authors == null) authors = new HashSet<>(authors);
        List<Author> authorList = new ArrayList<>(authors);
        authors.add(authorName);
        authorName.getWrittenBooks().add(this);
    }

    public Book() {
    }

    public Book(Set<Author> authors) {
        this.authors = authors;
    }

    public Book(String isbn, String title, int maxLoanDays) {
        setIsbn(isbn);
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }
    public Book(int bookId, String isbn, String title, int maxLoanDays) {
        this(isbn, title, maxLoanDays);
        this.bookId = bookId;
    }
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && maxLoanDays == book.maxLoanDays && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn, title, maxLoanDays);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }
}
