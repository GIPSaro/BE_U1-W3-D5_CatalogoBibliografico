package giorgiaipsaropassione.entitiesDAO;


import giorgiaipsaropassione.entities.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LoanDAO {
    private final EntityManager em;

    // CONSTRUCTOR
    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    // DAO METHODS

    public void saveLoan(Loan loan) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("Loan record saved successfully!");
        } catch (Exception e) {
            transaction.rollback(); // Ensure rollback on error
            System.err.println("Error saving loan: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    public Loan findLoanById(long id) {
        return em.find(Loan.class, id);
    }

    public List<Loan> findExpired() {
        String myQuery = "SELECT l FROM Loan l WHERE l.scheduledReturnDate < CURRENT_DATE AND l.returnDate IS NULL";
        TypedQuery<Loan> query = em.createQuery(myQuery, Loan.class);
        List<Loan> result = query.getResultList();
        if (result.isEmpty()) {
            System.out.println("No expired loans were found.");
        }
        return result;
    }

    public void findAndDeleteLoan(long id) {
        Loan found = findLoanById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Loan record deleted from DB!");
            } catch (Exception e) {
                transaction.rollback(); // Ensure rollback on error
                System.err.println("Error deleting loan: " + e.getMessage());
                e.printStackTrace(); // Print stack trace for debugging
            }
        } else {
            System.out.println("Loan record not found.");
        }
    }
}
