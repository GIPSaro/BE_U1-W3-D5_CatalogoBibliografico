package giorgiaipsaropassione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "literature_id")
    private Literature literatureOnLoan;
    private LocalDate checkoutDate;
    private LocalDate scheduledReturnDate;
    private LocalDate returnDate;

    // CONSTRUCTORS
    public Loan() {
    }

    public Loan(User user, Literature literatureOnLoan, LocalDate checkoutDate) {
        this.user = user;
        this.literatureOnLoan = literatureOnLoan;
        this.checkoutDate = checkoutDate;
        this.scheduledReturnDate = LocalDate.from(checkoutDate).plusDays(30);
    }

    // OVERRIDES

    @Override
    public String toString() {
        return "Loan{" +
                "userId=" + user.getId() +
                ", literatureOnLoan=" + literatureOnLoan +
                ", checkoutDate=" + checkoutDate +
                ", scheduledReturnDate=" + scheduledReturnDate +
                ", returnDate=" + returnDate +
                '}';
    }


    // GETTERS

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Literature getLiteratureOnLoan() {
        return literatureOnLoan;
    }

    public void setLiteratureOnLoan(Literature literatureOnLoan) {
        this.literatureOnLoan = literatureOnLoan;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    // SETTERS

    public LocalDate getScheduledReturnDate() {
        return scheduledReturnDate;
    }

    public void setScheduledReturnDate(LocalDate scheduledReturnDate) {
        this.scheduledReturnDate = scheduledReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}