package giorgiaipsaropassione;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogobibliograficojpa");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
