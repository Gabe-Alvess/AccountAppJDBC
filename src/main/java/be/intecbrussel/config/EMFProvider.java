package be.intecbrussel.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFProvider {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEMF() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        }

        return entityManagerFactory;
    }

    private EMFProvider() {
    }
}
