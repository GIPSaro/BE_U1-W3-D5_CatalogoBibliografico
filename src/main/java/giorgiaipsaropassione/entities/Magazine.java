package giorgiaipsaropassione.entities;

import giorgiaipsaropassione.utilities.Periodicity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Magazine extends Literature {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    // CONSTRUCTORS
    public Magazine() {
    }

    public Magazine(String isbnCode, String title, LocalDate publicationDate, int numberOfPages, Periodicity periodicity) {
        super(isbnCode, title, publicationDate, numberOfPages);
        this.periodicity = periodicity;
    }

    // OVERRIDES

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", isbnCode='" + isbnCode + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", numberOfPages=" + numberOfPages +
                '}';
    }


    // GETTERS

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    // SETTERS


    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }
}