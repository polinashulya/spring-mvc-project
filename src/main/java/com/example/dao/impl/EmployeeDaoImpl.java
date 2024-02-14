package com.example.dao.impl;

import com.example.dao.EmployeeDao;
import com.example.entity.EmployeeEntity;
import com.example.entity.EmployeeEntity;
import com.example.exception.DAOException;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger logger = LogManager.getLogger(EmployeeDaoImpl.class);
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byEmail";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<EmployeeEntity> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM EmployeeEntity e WHERE e.deleted = false", EmployeeEntity.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error while finding all employees", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public List<EmployeeEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {

        Session session = null;
        List<EmployeeEntity> employees = new ArrayList<>();

        try {
            session = sessionFactory.openSession();

            int offset = Optional.ofNullable(page)
                    .filter(p -> !p.isEmpty())
                    .map(Integer::parseInt)
                    .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                    .orElse(0);


            String filterAndSearchHql = getFilterAndSearchHql(countryId, search);
            String sortSql = getSortingHql(sortBy, sortType);
            String hql = "FROM EmployeeEntity e JOIN FETCH e.positions JOIN FETCH e.procedures " + filterAndSearchHql + sortSql;

            TypedQuery<EmployeeEntity> query = session.createQuery(hql, EmployeeEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));

            String myHQL = query.unwrap(org.hibernate.query.Query.class).getQueryString();
            System.out.println(myHQL);

            employees = query.getResultList();

        } catch (Exception e) {
            throw new DAOException("Error while finding all employees with pagination", e);
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }


    @Override
    public EmployeeEntity getById(Long id) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM EmployeeEntity e JOIN FETCH e.positions WHERE e.id = :employeeId AND e.deleted = false", EmployeeEntity.class)
                    .setParameter("employeeId", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding employee by ID", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public EmployeeEntity getByEmail(String email) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM EmployeeEntity e WHERE e.email = :email AND e.deleted = false", EmployeeEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding employee by email", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<EmployeeEntity> findByEmail(String email) {
        return Optional.ofNullable(getByEmail(email));
    }

    @Override
    public void save(EmployeeEntity employee) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(employee);
            logger.debug("User saved successfully");
        } catch (Exception e) {
            logger.error("Error while saving employee", e);
            throw new DAOException("Error while saving employee", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public void delete(Long id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            EmployeeEntity employee = session.get(EmployeeEntity.class, id);

            if (employee != null) {

                employee.setDeleted(true);

                session.update(employee);
                session.flush();
                transaction.commit();

                logger.debug("User deleted successfully");

            } else {
                logger.warn("User with id {} not found", id);
                throw new DAOException("User not found");
            }
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            logger.error("Error while deleting employee", e);
            throw new DAOException("Error while deleting employee", e);

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private static String getSortByOrDefault(String sortBy) {
        return sortBy == null ? "default" : sortBy;
    }

    @Override
    public String getFilterAndSearchHql(String countryId, String search) {

        StringBuilder hql = new StringBuilder(" WHERE e.deleted = false ");

//        if (countryId != null && !countryId.isEmpty()) {
//            hql.append(" AND e.country.id = ").append(countryId);
//        }

        if (search != null && !search.isEmpty()) {
            hql.append(" AND (e.email LIKE '%").append(search)
                    .append("%' OR e.name LIKE '%").append(search)
                    .append("%' OR e.surname LIKE '%")
                    .append(search).append("%')");
        }

        return hql.toString();
    }

    @Override
    public String getSortingHql(String sortBy, String sortType) {
        String alias = "e"; // Alias for the EmployeeEntity

        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN:
                return " ORDER BY " + alias + ".email " + (SORT_TYPE_ASC.equals(sortType) ? "ASC" : "DESC");
            case SORT_USERS_BY_SURNAME:
                return " ORDER BY " + alias + ".surname " + (SORT_TYPE_ASC.equals(sortType) ? "ASC" : "DESC");
            case SORT_USERS_BY_BIRTH_DATE:
                return " ORDER BY " + alias + ".birthDate " + (SORT_TYPE_ASC.equals(sortType) ? "ASC" : "DESC");
            case SORT_USERS_BY_ID:
                return " ORDER BY " + alias + ".id " + (SORT_TYPE_ASC.equals(sortType) ? "ASC" : "DESC");
            default:
                return " ORDER BY " + alias + ".id ASC";
        }
    }

    @Override
    public int getTotalResult(String filterAndSearchHql) {
        String hql = "SELECT COUNT(e.id) FROM EmployeeEntity e" + filterAndSearchHql;

        Session session = null;

        try {
            session = sessionFactory.openSession();
            return Math.toIntExact((Long) session.createQuery(hql).uniqueResult());

        } catch (Exception e) {
            logger.error("An error occurred while executing query: {}", hql, e);
            throw new DAOException("An error occurred while executing query", e);
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
