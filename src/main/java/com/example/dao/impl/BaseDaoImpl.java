package com.example.dao.impl;

import com.example.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public abstract class BaseDaoImpl<T> {

    protected SessionFactory sessionFactory;

    public List<T> findAll(Class<T> type) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + type.getName() + " t WHERE t.deleted = false", type)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error while finding all " + type.getName(), e);
        }
    }

    public T getById(Class<T> type, Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + type.getName() + " t JOIN FETCH t.country c WHERE t.id = :userId AND t.deleted = false", type)
                    .setParameter("userId", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding " + type.getName() + " by ID", e);
        }
    }


    public Optional<T> findById(Class<T> type, Long id) {
        return Optional.ofNullable(getById(type, id));
    }

    public void save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Error while saving " + entity.getClass().getName(), e);
        }
    }

    public void delete(Class<T> type, Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.get(type, id);
            if (entity != null) {
                session.delete(entity);
                session.getTransaction().commit();
            } else {
                throw new DAOException(type.getName() + " not found with id: " + id);
            }
        } catch (Exception e) {
            throw new DAOException("Error while deleting " + type.getName(), e);
        }
    }

}
