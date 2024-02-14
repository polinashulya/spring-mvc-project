package com.example.dao.impl;

import com.example.dao.ClientDao;
import com.example.dao.specification.ClientSpecification;
import com.example.entity.ClientEntity;
import com.example.exception.DAOException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ClientDaoImpl extends AbstractDaoImpl<ClientEntity> implements ClientDao, ClientSpecification {

    private static final String GET_CLIENT_BY_ID = "FROM ClientEntity c WHERE c.id = :id";

    private static final String GET_CLIENT_BY_EMAIL = "FROM ClientEntity c JOIN FETCH c.country c WHERE c.email = :email AND c.deleted = false";

    @Override
    public List<ClientEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {
        return executeQuery(session -> {
            CriteriaQuery<ClientEntity> criteriaQuery = buildCriteriaQuery(session, search, countryId, sortBy, sortType);
            TypedQuery<ClientEntity> query = session.createQuery(criteriaQuery);
            applyPagination(query, page, pageSize);
            return query.getResultList();
        });
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_CLIENT_BY_ID, id)
        );
    }

    @Override
    public ClientEntity getByEmail(String email) {
        return executeQuery(session ->
                session.createQuery(GET_CLIENT_BY_EMAIL, ClientEntity.class)
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
    public DeletionStatus delete(Long id) {
        return delete(id, ClientEntity.class);
    }

    @Override
    public int getTotalResult(String search, String countryId) {
        return executeQuery(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<ClientEntity> root = countQuery.from(ClientEntity.class);

            Predicate predicate = buildPredicate(builder, root, search, countryId);

            countQuery.select(builder.count(root)).where(predicate);

            return Math.toIntExact(session.createQuery(countQuery).uniqueResult());
        });
    }

}
