package com.example.dao.impl;

import com.example.dao.AbstractDao;
import com.example.entity.DeletionStatus;
import com.example.entity.core.AbstractBaseEntity;
import com.example.exception.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public abstract class AbstractDaoImpl<E extends AbstractBaseEntity> implements AbstractDao<E> {

    @Autowired
    protected SessionFactory sessionFactory;
    private final Class<E> clazz;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl() {
        // Determine the class of E at runtime
        this.clazz = (Class<E>) ((ParameterizedType)
                this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public E getById(String sql, Long id) {
        return executeQuery(session ->
                session.createQuery(sql, clazz)
                        .setParameter("id", id)
                        .uniqueResult());
    }

    @Override
    public Optional<E> findById(String sql, Long id) {
        return Optional.ofNullable(
                getById(sql, id)
        );
    }

    @Override
    public List<E> findAll(String sql) {
        return executeQuery(session ->
                session.createQuery(sql, clazz)
                        .getResultList());
    }

    @Override
    public void save(E entity) {
        executeTransaction(session -> {
            session.persist(entity);
            return entity;
        });
    }

    public DeletionStatus softDelete(String sql, Long id) {
        try {
            return executeTransaction(session -> {
                session.createQuery(sql)
                        .setParameter("id", id)
                        .executeUpdate();
                return DeletionStatus.NO_CONTENT;
            });
        } catch (DAOException e) {
            return DeletionStatus.NOT_FOUND;
        }
    }

    protected <T> T executeQuery(Function<Session, T> queryFunction) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return queryFunction.apply(session);
        } catch (Exception e) {
            throw new DAOException("Error occurred while executing query", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public <T> T executeTransaction(Function<Session, T> function) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T result = function.apply(session);
            session.flush();
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Transaction failed", e);
        }
    }

}

