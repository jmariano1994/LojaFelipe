package com.felipe.loja.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

public class Repositorio<Pk, T> {

    private EntityManager entityManager;

    public void Salvar(T object) {

        this.entityManager = EntityManagerFactory.setOn();
        this.entityManager.persist(object);
        this.entityManager.close();
    }

    public void Atualizar(T object) {

        this.entityManager = EntityManagerFactory.setOn();
        this.entityManager.merge(object);
        this.entityManager.close();
    }

    public void Remover(T object) {

        this.entityManager = EntityManagerFactory.setOn();
        this.entityManager.remove(object);
        this.entityManager.close();
    }

    public T Listar(Pk pk) {

        this.entityManager = EntityManagerFactory.setOn();
        T obj = (T) entityManager.find(getTypeClass(), pk);
        this.entityManager.close();
        return obj;
    }

    public List<T> ListarTodos() {

        this.entityManager = EntityManagerFactory.setOn();
        List<T> objs = entityManager.createQuery(("FROM " + getTypeClass().getName())).getResultList();
        this.entityManager.close();

        return objs;
    }

    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }
}
