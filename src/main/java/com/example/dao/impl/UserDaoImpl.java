package com.example.dao.impl;

import com.example.dao.ClientDao;
import com.example.dao.UserDao;
import com.example.dao.specification.ClientSpecification;
import com.example.entity.ClientEntity;
import com.example.entity.DeletionStatus;
import com.example.entity.UserEntity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDaoImpl<ClientEntity> implements UserDao, ClientSpecification {

    private static final String GET_CLIENT_BY_ID = "FROM ClientEntity c WHERE c.id = :id";

    private static final String SOFT_DELETE_CLIENT_BY_ID = "UPDATE ClientEntity c SET c.deleted = true WHERE c.id = :id";

    private static final String GET_USER_BY_EMAIL = "FROM UserEntity u WHERE u.email = :email AND u.deleted = false";

//    @Override
//    public List<ClientEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize) {
//        return executeQuery(session -> {
//            CriteriaQuery<ClientEntity> criteriaQuery = buildCriteriaQuery(session, search, countryId, sortBy, sortType);
//            TypedQuery<ClientEntity> query = session.createQuery(criteriaQuery);
//            applyPagination(query, page, pageSize);
//            return query.getResultList();
//        });
//    }

//    @Override
//    public Optional<ClientEntity> findById(Long id) {
//        return findById(GET_CLIENT_BY_ID, id);
//    }
//
    @Override
    public UserEntity getByEmail(String email) {
        return executeQuery(session ->
                session.createQuery(GET_USER_BY_EMAIL, UserEntity.class)
                        .setParameter("email", email)
                        .uniqueResult());

    }

//    @Override
//    public void save(ClientEntity client) {
//        super.save(client);
//    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(getByEmail(email));
    }

//    @Override
//    public DeletionStatus softDelete(Long id) {
//        return softDelete(SOFT_DELETE_CLIENT_BY_ID, id);
//    }
//
//    @Override
//    public int getTotalResult(String search, String countryId) {
//        return executeQuery(session -> {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//            Root<ClientEntity> root = countQuery.from(ClientEntity.class);
//
//            Predicate predicate = buildPredicate(builder, root, search, countryId);
//
//            countQuery.select(builder.count(root)).where(predicate);
//
//            return Math.toIntExact(session.createQuery(countQuery).uniqueResult());
//        });
//    }

}
