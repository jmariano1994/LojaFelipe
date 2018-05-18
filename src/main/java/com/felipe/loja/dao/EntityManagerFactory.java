package com.felipe.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/*Prepara a Conexao no Banco de dados*/

public class EntityManagerFactory {

    public static EntityManager setOn() {
        return Persistence.createEntityManagerFactory("org.hibernate.loja.jpa").createEntityManager();
    }

    public static void setOff(EntityManager entityManager) {
        entityManager.clear();
        entityManager.close();
    }
}
