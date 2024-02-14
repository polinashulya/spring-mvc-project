package com.example.dao.impl;

import com.example.dao.ClientDao;
import com.example.entity.ClientEntity;
import com.example.exception.DAOException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClientDaoImpl extends AbstractDaoImpl<ClientEntity> implements ClientDao {

    private static final Logger logger = LogManager.getLogger(ClientDaoImpl.class);
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byEmail";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";
    private static final String GET_CLIENT_BY_ID = "FROM ClientEntity c WHERE c.id = :id";

    @Override
    public List<ClientEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {
        return executeQuery(session -> {
            CriteriaQuery<ClientEntity> criteriaQuery = buildCriteriaQuery(session, search, countryId, sortBy, sortType);
            TypedQuery<ClientEntity> query = session.createQuery(criteriaQuery);
            applyPagination(query, page, pageSize);
            return query.getResultList();
        });
    }

    private CriteriaQuery<ClientEntity> buildCriteriaQuery(Session session, String search, String countryId, String sortBy, String sortType) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);

        Predicate predicate = buildPredicate(builder, root, search, countryId);
        criteriaQuery.where(predicate);

        applySorting(builder, criteriaQuery, root, sortBy, sortType);

        return criteriaQuery;
    }

    private Predicate buildPredicate(CriteriaBuilder builder, Root<ClientEntity> root, String search, String countryId) {
        Predicate predicate = builder.conjunction();
        if (countryId != null && !countryId.isEmpty()) {
            predicate = builder.and(predicate, builder.equal(root.get("country").get("id"), Long.parseLong(countryId)));
        }
        if (search != null && !search.isEmpty()) {
            predicate = builder.and(predicate,
                    builder.or(
                            builder.like(root.get("email"), "%" + search + "%"),
                            builder.like(root.get("name"), "%" + search + "%"),
                            builder.like(root.get("surname"), "%" + search + "%")
                    )
            );
        }
        predicate = builder.and(predicate, builder.equal(root.get("deleted"), false));
        return predicate;
    }

    private void applySorting(CriteriaBuilder builder, CriteriaQuery<ClientEntity> criteriaQuery, Root<ClientEntity> root, String sortBy, String sortType) {
        if (sortBy != null && !sortBy.isEmpty()) {
            Expression<?> orderByExpression;
            switch (sortBy) {
                case SORT_USERS_BY_LOGIN:
                    orderByExpression = root.get("email");
                    break;
                case SORT_USERS_BY_SURNAME:
                    orderByExpression = root.get("surname");
                    break;
                case SORT_USERS_BY_BIRTH_DATE:
                    orderByExpression = root.get("birthDate");
                    break;
                case SORT_USERS_BY_ID:
                default:
                    orderByExpression = root.get("id");
                    break;
            }
            criteriaQuery.orderBy(sortType.equals(SORT_TYPE_ASC) ? builder.asc(orderByExpression) : builder.desc(orderByExpression));
        }
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_CLIENT_BY_ID, id)
        );
    }

    @Override
    public ClientEntity getByEmail(String email) {
        return executeQuery(session -> session.createQuery(
                        "FROM ClientEntity u JOIN FETCH u.country c WHERE u.email = :email AND u.deleted = false",
                        ClientEntity.class)
                .setParameter("email", email)
                .uniqueResult());

    }


    @Override
    public Optional<ClientEntity> findByEmail(String email) {
        return Optional.ofNullable(getByEmail(email));
    }

    @Override
    public void save(ClientEntity client) {
        super.save(client);
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


    @Override
    public String getFilterAndSearchHql(String countryId, String search) {

        StringBuilder hql = new StringBuilder(" WHERE c.deleted = false ");

        if (countryId != null && !countryId.isEmpty()) {
            hql.append(" AND c.country.id = ").append(countryId);
        }

        if (search != null && !search.isEmpty()) {
            hql.append(" AND (c.email LIKE '%").append(search)
                    .append("%' OR c.name LIKE '%").append(search)
                    .append("%' OR c.surname LIKE '%")
                    .append(search).append("%')");
        }

        return hql.toString();
    }

    @Override
    public int getTotalResult(String filterAndSearchHql) {
        return executeQuery(session -> {
            String hql = "SELECT COUNT(c.id) FROM ClientEntity c" + filterAndSearchHql;
            return Math.toIntExact((Long) session.createQuery(hql).uniqueResult());
        });
    }

}
