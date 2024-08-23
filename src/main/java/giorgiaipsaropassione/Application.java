package giorgiaipsaropassione;

import com.github.javafaker.Faker;
import giorgiaipsaropassione.entities.User;
import giorgiaipsaropassione.entitiesDAO.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


// nel progetto c'è una cartella GraficoSQL in cui c'è l'export .jpeg e l'export .sql,
// inoltre qui c'è link del grafico di Draw.io per poter visualizzare i commenti:
// https://drawsql.app/teams/noe-5/diagrams/catalogo-bibliografico

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliograficojpa");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAO(em);

        // Genera e salva dati di test
        generateTestData(userDAO);

        em.close();
        emf.close();
    }

    private static void generateTestData(UserDAO userDAO) {
        Faker faker = new Faker();

        // Genera e salva 5 utenti in modo randomico con faker

        for (int i = 0; i < 5; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            LocalDate birthday = LocalDate.of(faker.number().numberBetween(1950, 2000), faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28));
            long loyaltyCardNumber = faker.number().numberBetween(10000, 99999);

            User user = new User(name, surname, birthday, loyaltyCardNumber);
            userDAO.saveUser(user);
        }
        System.out.println("Test data generated and saved.");
    }
}