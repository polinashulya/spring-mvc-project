package com.example.dao.impl;

import com.example.dao.ClientDao;
import com.example.entity.ClientEntity;
import com.example.entity.ClientEntity;
import com.example.exception.DAOException;
import com.example.service.impl.UserServiceImpl;
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
public class ClientDaoImpl implements ClientDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byLogin";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    private final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<ClientEntity> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM ClientEntity u WHERE u.deleted = false", ClientEntity.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error while finding all clients", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public List<ClientEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {

        Session session = null;
        List<ClientEntity> clients = new ArrayList<>();

        try {
            session = sessionFactory.openSession();

            int offset = Optional.ofNullable(page)
                    .filter(p -> !p.isEmpty())
                    .map(Integer::parseInt)
                    .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                    .orElse(0);


            String filterAndSearchHql = getFilterAndSearchHql(countryId, search);
            String sortSql = getSortingHql(sortBy, sortType);
            String hql = "FROM ClientEntity u JOIN FETCH u.country c " + filterAndSearchHql + sortSql;

            TypedQuery<ClientEntity> query = session.createQuery(hql, ClientEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));

            String myHQL = query.unwrap(org.hibernate.query.Query.class).getQueryString();

            clients = query.getResultList();

        } catch (Exception e) {
            throw new DAOException("Error while finding all clients with pagination", e);
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return clients;
    }


    @Override
    public ClientEntity getById(Long id) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM ClientEntity u JOIN FETCH u.country c WHERE u.id = :clientId AND u.deleted = false", ClientEntity.class)
                    .setParameter("clientId", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding client by ID", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<ClientEntity> findById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public ClientEntity getByLogin(String login) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM ClientEntity u JOIN FETCH u.country c WHERE u.login = :login AND u.deleted = false", ClientEntity.class)
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding client by login", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<ClientEntity> findByLogin(String login) {
        return Optional.ofNullable(getByLogin(login));
    }

    @Override
    public void save(ClientEntity client) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(client);
            logger.debug("User saved successfully");
        } catch (Exception e) {
            logger.error("Error while saving client", e);
            throw new DAOException("Error while saving client", e);
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

            ClientEntity client = session.get(ClientEntity.class, id);

            if (client != null) {

                client.setDeleted(true);

                session.update(client);
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

            logger.error("Error while deleting client", e);
            throw new DAOException("Error while deleting client", e);

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

        StringBuilder hql = new StringBuilder(" WHERE u.deleted = false ");

        if (countryId != null && !countryId.isEmpty()) {
            hql.append(" AND u.country.id = ").append(countryId);
        }

        if (search != null && !search.isEmpty()) {
            hql.append(" AND (u.login LIKE '%").append(search)
                    .append("%' OR u.name LIKE '%").append(search)
                    .append("%' OR u.surname LIKE '%")
                    .append(search).append("%')");
        }

        return hql.toString();
    }

    @Override
    public String getSortingHql(String sortBy, String sortType) {
        String alias = "u"; // Alias for the ClientEntity

        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN:
                return " ORDER BY " + alias + ".login " + (SORT_TYPE_ASC.equals(sortType) ? "ASC" : "DESC");
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
        String hql = "SELECT COUNT(u.id) FROM ClientEntity u" + filterAndSearchHql;

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
