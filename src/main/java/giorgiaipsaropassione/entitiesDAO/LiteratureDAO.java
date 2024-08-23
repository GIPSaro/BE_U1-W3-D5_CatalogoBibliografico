package giorgiaipsaropassione.entitiesDAO;

import com.github.javafaker.Faker;
import giorgiaipsaropassione.entities.Book;
import giorgiaipsaropassione.entities.Literature;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.TransactionalException;

import java.util.List;

public class LiteratureDAO {
    private final EntityManager em;
    private final Faker faker;

    // CONSTRUCTOR
    public LiteratureDAO(EntityManager em) {
        this.em = em;
        this.faker = new Faker();  // Initialize Faker
    }

    // DAO METHODS

    public void saveLiterature(Literature literature) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(literature);
            transaction.commit();
            System.out.println(literature.getTitle() + ", ISBN " + literature.getIsbnCode() + ", saved successfully!");
        } catch (TransactionalException tErr) {
            System.err.println(tErr.getMessage());
        }
    }

    public Literature findByIsbn(String isbn) {
        String myQuery = "SELECT l FROM Literature l WHERE l.isbnCode LIKE :isbnCode";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("isbnCode", isbn);
        Literature literature = query.getResultStream().findFirst().orElse(null);
        if (literature == null) System.out.println("Literature item not found.");
        return literature;
    }

    public List<Literature> findByYear(int year) {
        String myQuery = "SELECT l FROM Literature l WHERE FUNCTION('YEAR', l.publicationDate) = :year";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("year", year);
        List<Literature> result = query.getResultList();
        if (result.isEmpty()) System.out.println("No literature published in " + year + " was found.");
        return result;
    }

    public List<Book> findByAuthor(String author) {
        String myQuery = "SELECT b FROM Book b WHERE b.author LIKE :author";
        TypedQuery<Book> query = em.createQuery(myQuery, Book.class);
        query.setParameter("author", "%" + author + "%");
        List<Book> result = query.getResultList();
        if (result.isEmpty()) System.out.println("There are no books from " + author + " in store.");
        return result;
    }

    public List<Literature> findByTitle(String title) {
        String myQuery = "SELECT l FROM Literature l WHERE l.title LIKE :title";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("title", "%" + title + "%");
        List<Literature> result = query.getResultList();
        if (result.isEmpty()) System.out.println("No title matches your research.");
        return result;
    }

    public List<Literature> findByCard(long card) {
        String myQuery = "SELECT l FROM Literature l WHERE l.id IN (SELECT l.literatureOnLoan FROM Loan l WHERE l.user IN (SELECT u FROM User u WHERE u.loyaltyCardNumber = :card))";
        TypedQuery<Literature> query = em.createQuery(myQuery, Literature.class);
        query.setParameter("card", card);
        List<Literature> result = query.getResultList();
        if (result.isEmpty()) System.out.println("User card " + card + " has no linked loans.");
        return result;
    }

    public void findAndDeleteByIsbn(String isbn) {
        Literature itemToRemove = findByIsbn(isbn);
        if (itemToRemove != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(itemToRemove);
                transaction.commit();
                System.out.println("Literature item successfully deleted from DB!");
            } catch (TransactionalException tErr) {
                System.err.println(tErr.getMessage());
            }
        } else {
            System.out.println("Literature item not found.");
        }
    }

    // NEW METHOD FOR GENERATING RANDOM LITERATURE
    public void generateRandomLiterature(int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            Literature literature = new Literature();
            literature.setTitle(faker.book().title());
            literature.setIsbnCode(faker.code().isbn13());
            literature.setPublicationYear(faker.date().past(10000, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            saveLiterature(literature);
        }
    }
}
