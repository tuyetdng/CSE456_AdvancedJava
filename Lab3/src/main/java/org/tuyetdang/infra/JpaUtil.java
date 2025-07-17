package org.tuyetdang.infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf;

    //Read one time
    static {
        try {
            emf = Persistence.createEntityManagerFactory("pu1-mysql-masterapp");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private JpaUtil() {
        // Prevent instantiation
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
