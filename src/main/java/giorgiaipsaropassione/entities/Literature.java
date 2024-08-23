package giorgiaipsaropassione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "literature")
public class Literature {
    @Id
    @GeneratedValue
    protected long id;

    @Column(nullable = false, unique = true)
    protected String isbnCode;
    @Column(nullable = false)
    protected String title;
    @Column(nullable = false)
    protected LocalDate publicationDate;
    @Column(nullable = false)
    protected int numberOfPages;

    // CONSTRUCTORS
    public Literature() {
    }

    public Literature(String isbnCode, String title, LocalDate publicationDate, int numberOfPages) {
        this.isbnCode = isbnCode;
        this.title = title;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Literature{" +
                "id=" + id +
                ", isbnCode='" + isbnCode + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    // GETTERS

    public long getId() {
        return id;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // SETTERS

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationDate = publicationYear;
    }
}