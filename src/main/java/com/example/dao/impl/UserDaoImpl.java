package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.entity.CountryEntity;
import com.example.entity.UserEntity;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byLogin";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM UserEntity u WHERE u.deleted = false", UserEntity.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DAOException("Error while finding all users", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {

        Session session = null;
        List<UserEntity> users = new ArrayList<>();

        try {
            session = sessionFactory.openSession();

            int offset = Optional.ofNullable(page)
                    .map(Integer::parseInt)
                    .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                    .orElse(0);


            String filterAndSearchHql = getFilterAndSearchHql(countryId, search);
            String sortSql = getSortingHql(sortBy, sortType);
            String hql = "FROM UserEntity u JOIN FETCH u.country c " + filterAndSearchHql + sortSql;

            TypedQuery<UserEntity> query = session.createQuery(hql, UserEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));

            logger.debug(query.unwrap(org.hibernate.query.Query.class).getQueryString());

            users = query.getResultList();

        } catch (Exception e) {
            throw new DAOException("Error while finding all users with pagination", e);
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }


    @Override
    public UserEntity getById(Long id) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM UserEntity u JOIN FETCH u.country c WHERE u.id = :userId AND u.deleted = false", UserEntity.class)
                    .setParameter("userId", id)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding user by ID", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public UserEntity getByLogin(String login) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM UserEntity u JOIN FETCH u.country c WHERE u.login = :login AND u.deleted = false", UserEntity.class)
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (Exception e) {
            throw new DAOException("Error while finding user by login", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }


    @Override
    public Optional<UserEntity> findByLogin(String login) {
        return Optional.ofNullable(getByLogin(login));
    }

    private static UserEntity getUser(ResultSet set) {
        try {
            return UserEntity.builder()
                    .id(set.getLong(1))
                    .login(set.getString(2))
                    .name(set.getString(3))
                    .surname(set.getString(4))
                    .birthDate((set.getDate(5)).toLocalDate())
                    .banned(set.getBoolean(6))
                    .country(
                            CountryEntity.builder()
                                    .id(set.getLong(7))
                                    .name(set.getString(8))
                                    .build()
                    )
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping User from ResultSet", e);
        }
    }


    @Override
    @Transactional
    public void save(UserEntity user) {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.saveOrUpdate(user);
            logger.debug("User saved successfully");
        } catch (Exception e) {
            logger.error("Error while saving user", e);
            throw new DAOException("Error while saving user", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    @Transactional
    public void delete(Long id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            UserEntity user = session.get(UserEntity.class, id);

            if (user != null) {

                user.setDeleted(true);

                session.update(user);
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

            logger.error("Error while deleting user", e);
            throw new DAOException("Error while deleting user", e);

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
        String alias = "u"; // Alias for the UserEntity

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
        String hql = "SELECT COUNT(u.id) FROM UserEntity u" + filterAndSearchHql;

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
