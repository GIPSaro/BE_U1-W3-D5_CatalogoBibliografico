package giorgiaipsaropassione;

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
        UserDAO ud = new UserDAO(em);
        User giorgia = new User("Giorgia", "Ipsaro Passione", LocalDate.of(1989, 5, 28), 12345);
        long userId = 2;

        ud.saveUser(giorgia);
//      ud.findAndDeleteUser(userId);

    }
}
