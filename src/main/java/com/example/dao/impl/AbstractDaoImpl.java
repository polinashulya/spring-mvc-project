package com.example.dao.impl;

import com.example.dao.AbstractDao;
import com.example.entity.core.AbstractBaseEntity;
import com.example.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
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
    public List<E> findAll(String sql) {
        return executeQuery(session ->
                session.createQuery(sql, clazz)
                        .getResultList());
    }

    @Override
    public <E> void save(E entity) {
        executeQuery(session -> {
            session.saveOrUpdate(entity);
            return entity;
        });
    }

    public <T> DeletionStatus delete(Long id, Class<T> entityType) {
        try {
            return executeQuery(session -> {
                Transaction transaction = session.beginTransaction();
                T entity = session.get(entityType, id);
                if (entity == null) {
                    throw new DAOException("Entity not found");
                }
                Method setDeletedMethod;
                try {
                    setDeletedMethod = entity.getClass().getMethod("setDeleted", boolean.class);
                } catch (NoSuchMethodException e) {
                    throw new DAOException("Method 'setDeleted' not found in entity class", e);
                }
                try {
                    setDeletedMethod.invoke(entity, true);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new DAOException("Error invoking 'setDeleted' method", e);
                }
                session.update(entity);
                session.flush();
                transaction.commit();
                return DeletionStatus.SUCCESS;
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
}
