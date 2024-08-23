package giorgiaipsaropassione;

import com.github.javafaker.Faker;
import giorgiaipsaropassione.entities.Book;
import giorgiaipsaropassione.entities.Loan;
import giorgiaipsaropassione.entities.Magazine;
import giorgiaipsaropassione.entities.User;
import giorgiaipsaropassione.entitiesDAO.LiteratureDAO;
import giorgiaipsaropassione.entitiesDAO.LoanDAO;
import giorgiaipsaropassione.entitiesDAO.UserDAO;
import giorgiaipsaropassione.utilities.Periodicity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// nel progetto c'è una cartella GraficoSQL in cui c'è l'export .jpeg e l'export .sql,
// inoltre qui c'è link del grafico di Draw.io per poter visualizzare i commenti:
// https://drawsql.app/teams/noe-5/diagrams/catalogo-bibliografico

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliograficojpa");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        UserDAO ud = new UserDAO(em);
        LiteratureDAO ld = new LiteratureDAO(em);
        LoanDAO lod = new LoanDAO(em);

        // Genera e salva dati di test
        generateTestData(ud, ld, lod);


        // ELIMINARE TRAMITE ISBN

        String isbnToDelete = "ISBN0"; // Inserisci l'ISBN che vuoi eliminare
        ld.findAndDeleteByIsbn(isbnToDelete);

        // Pulisce il contesto di persistenza
        em.clear();


        // RICERCA PER ISBN
        System.out.println("Find by ISBN:");
        System.out.println(ld.findByIsbn("ISBN0"));


        // RICERCA TRAMITE TITOLO

        System.out.println("Find by Title:");
        System.out.println(ld.findByTitle("Paths"));


        // RICERCA PER AUTORE

        System.out.println("Find by Author:");
        System.out.println(ld.findByAuthor("Eliseo"));

        // RICERCA PER ANNO

        System.out.println("Find by Year:");
        System.out.println(ld.findByYear(2007));

        // RICERCA PER TESSERA

        System.out.println("Find by Card:");
        System.out.println(ld.findByCard(95921));
//
//        // RICERCA PER PRESTITI SCADUTI
//
        System.out.println("Expired Loans:");
        System.out.println(lod.findExpired());

        em.close();
        emf.close();
    }

    private static void generateTestData(UserDAO userDAO, LiteratureDAO literatureDAO, LoanDAO loanDAO) {
        Faker faker = new Faker();
        Random random = new Random();


        List<User> users = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();

        // Genera e salva 5 utenti
        for (int i = 0; i < 5; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            LocalDate birthday = LocalDate.of(
                    faker.number().numberBetween(1950, 2000),
                    faker.number().numberBetween(1, 12),
                    faker.number().numberBetween(1, 28)
            );
            long loyaltyCardNumber = faker.number().numberBetween(10000, 99999);

            User user = new User(name, surname, birthday, loyaltyCardNumber);
//            userDAO.saveUser(user);
//            users.add(user);
        }

        // Genera e salva 10 libri
        for (int i = 0; i < 10; i++) {
            String title = faker.book().title();
            Book book = new Book(
                    "ISBN" + i,
                    title,
                    LocalDate.of(2000 + (i % 20), (i % 12) + 1, (i % 28) + 1),
                    100 + i,
                    faker.book().author(),
                    faker.book().genre()
            );
//            literatureDAO.saveLiterature(book);
//            books.add(book);
        }

        // Genera e salva 5 magazine
        for (int i = 0; i < 5; i++) {
            String title = faker.book().title();
            Periodicity periodicity = Periodicity.values()[random.nextInt(Periodicity.values().length)];
            Magazine magazine = new Magazine(
                    "ISBN_MAG" + i,
                    title,
                    LocalDate.of(2000 + (i % 20), (i % 12) + 1, (i % 28) + 1),
                    50 + i,
                    periodicity
            );
//            literatureDAO.saveLiterature(magazine);
//            magazines.add(magazine);
        }

        // Genera e salva prestiti
        for (int i = 0; i < 5; i++) {
            if (!users.isEmpty() && !books.isEmpty()) {
                User user = users.get(random.nextInt(users.size()));
                Book book = books.get(random.nextInt(books.size()));
                LocalDate checkoutDate = LocalDate.now().plusMonths(i);
                Loan loan = new Loan(user, book, checkoutDate);

                // Imposta returnDate su alcuni prestiti
                if (random.nextBoolean()) { // 50% probabilità di avere una data di ritorno
                    loan.setReturnDate(LocalDate.now().minusDays(random.nextInt(30) + 1));
                }

//                loanDAO.saveLoan(loan);
            }
        }

        System.out.println("Test data generated and saved.");
    }
}