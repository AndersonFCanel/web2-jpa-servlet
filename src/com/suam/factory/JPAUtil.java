package com.suam.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Web2Sistema");

    public EntityManager getEntityManager() {
         return emf.createEntityManager();
    }
}