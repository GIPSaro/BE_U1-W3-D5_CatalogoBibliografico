package giorgiaipsaropassione.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Book extends Literature {
    private String author;
    private String genre;

    // CONSTRUCTORS
    public Book() {
    }

    public Book(String isbnCode, String title, LocalDate publicationDate, int numberOfPages, String author, String genre) {
        super(isbnCode, title, publicationDate, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    // OVERRIDES

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isbnCode='" + isbnCode + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                '}';
    }


    // GETTERS

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // SETTERS

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}