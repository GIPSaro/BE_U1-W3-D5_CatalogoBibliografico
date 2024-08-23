package giorgiaipsaropassione.entitiesDAO;

import com.github.javafaker.Faker;
import giorgiaipsaropassione.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.TransactionalException;

import java.time.LocalDate;
import java.time.ZoneId;

public class UserDAO {
    private final EntityManager em;
    private final Faker faker = new Faker(); // Aggiungi Faker

    // CONSTRUCTOR
    public UserDAO(EntityManager em) {
        this.em = em;
    }

    // DAO METHODS

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println(user.getName() + " " + user.getSurname() + " saved successfully!");
        } catch (TransactionalException tErr) {
            System.err.println(tErr.getMessage());
        }
    }

    public User findUserById(long id) {
        return em.find(User.class, id);
    }

    public void findAndDeleteUser(long id) {
        User found = findUserById(id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("User successfully deleted from DB!");
            } catch (TransactionalException tErr) {
                System.err.println(tErr.getMessage());
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void generateAndSaveFakeUsers(int numberOfUsers) {
        for (int i = 0; i < numberOfUsers; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            LocalDate birthDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long loyaltyCardNumber = faker.number().randomNumber(5, true);

            User user = new User(name, surname, birthDate, loyaltyCardNumber);
            saveUser(user);
        }
    }
}
