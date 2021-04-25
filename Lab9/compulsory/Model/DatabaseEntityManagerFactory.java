package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseEntityManagerFactory {
    private static DatabaseEntityManagerFactory myDataBase = null;
    private EntityManager em = null;

    private DatabaseEntityManagerFactory() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("default");
        em = factory.createEntityManager();
    }

    public static DatabaseEntityManagerFactory getInstance() {
        if (myDataBase == null)
            myDataBase = new DatabaseEntityManagerFactory();

        return myDataBase;
    }

    public EntityManager getEm() {
        return em;
    }
}
