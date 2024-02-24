package com.example.dao.impl;

import com.example.dao.EmployeeDao;
import com.example.dao.specification.EmployeeSpecification;
import com.example.entity.ClientEntity;
import com.example.entity.EmployeeEntity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeDaoImpl extends AbstractDaoImpl<EmployeeEntity> implements EmployeeDao, EmployeeSpecification {

    private static final String GET_EMPLOYEE_BY_ID = "FROM EmployeeEntity e WHERE e.id = :id";

    private static final String SOFT_DELETE_EMPLOYEE_BY_BY_ID = "UPDATE EmployeeEntity e SET e.deleted = true WHERE e.id = :id";

    private static final String GET_EMPLOYEE_BY_BY_EMAIL = "FROM EmployeeEntity e JOIN FETCH e.country c WHERE e.email = :email AND e.deleted = false";

    @Transactional
    @Override
    public List<EmployeeEntity> findAll(String search, String countryId, String sortBy, String sortType, String page, String pageSize, String positionId, String procedureId) {
        return executeQuery(session -> {
            CriteriaQuery<EmployeeEntity> criteriaQuery = buildCriteriaQuery(session, search, countryId, sortBy, sortType, positionId,procedureId);
            TypedQuery<EmployeeEntity> query = session.createQuery(criteriaQuery);
            applyPagination(query, page, pageSize);
            return query.getResultList();
        });
    }

    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return findById(GET_EMPLOYEE_BY_ID, id);
    }

    @Override
    public EmployeeEntity getByEmail(String email) {
        return executeQuery(session ->
                session.createQuery(GET_EMPLOYEE_BY_BY_EMAIL, EmployeeEntity.class)
                        .setParameter("email", email)
                        .uniqueResult());

    }


    @Override
    public Optional<EmployeeEntity> findByEmail(String email) {
        return Optional.ofNullable(getByEmail(email));
    }

    @Override
    public void save(EmployeeEntity employee) {
        super.save(employee);
    }

    @Override
    public DeletionStatus softDelete(Long id) {
        return softDelete(SOFT_DELETE_EMPLOYEE_BY_BY_ID, id);
    }

    @Override
    public int getTotalResult(String search, String countryId, String positionId, String procedureId) {
        return executeQuery(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<EmployeeEntity> root = countQuery.from(EmployeeEntity.class);

            Predicate predicate = buildPredicate(builder, root, search, countryId, positionId,procedureId);

            countQuery.select(builder.count(root)).where(predicate);

            return Math.toIntExact(session.createQuery(countQuery).uniqueResult());
        });
    }

}
