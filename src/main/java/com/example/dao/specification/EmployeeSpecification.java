package com.example.dao.specification;

import com.example.entity.EmployeeEntity;
import com.example.entity.EmployeePositionEntity;
import com.example.entity.ProcedureEntity;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.Optional;

public interface EmployeeSpecification extends AbstractSpecification<EmployeeEntity>{

    String SORT_TYPE_ASC = "ASC";
    String SORT_USERS_BY_LOGIN = "byEmail";
    String SORT_USERS_BY_HIRE_DATE = "byHireDate";

    default CriteriaQuery<EmployeeEntity> buildCriteriaQuery(Session session, String search, String countryId, String sortBy, String sortType, String positionId, String procedureId) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = builder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);
        root.fetch("positions", JoinType.LEFT);
        root.fetch("procedures", JoinType.LEFT);

        Predicate predicate = buildPredicate(builder, root, search, countryId, positionId, procedureId);
        criteriaQuery.where(predicate);

        applySorting(builder, criteriaQuery, root, sortBy, sortType);

        return criteriaQuery;
    }

    default Predicate buildPredicate(CriteriaBuilder builder, Root<EmployeeEntity> root, String search, String countryId, String positionId, String procedureId) {
        Predicate predicate = builder.conjunction();
        if (countryId != null && !countryId.isEmpty()) {
            predicate = builder.and(predicate, builder.equal(root.get("country").get("id"), Long.parseLong(countryId)));
        }
        if (search != null && !search.isEmpty()) {
            predicate = builder.and(predicate,
                    builder.or(
                            builder.like(root.get("email"), "%" + search + "%"),
                            builder.like(root.get("name"), "%" + search + "%"),
                            builder.like(root.get("surname"), "%" + search + "%"),
                            builder.like(root.get("phoneNumber"), "%" + search + "%")
                    )
            );
        }

        if (positionId != null && !positionId.isEmpty()) {
            SetJoin<EmployeeEntity, EmployeePositionEntity> positionJoin = root.joinSet("positions", JoinType.INNER);
            predicate = builder.and(predicate, builder.equal(positionJoin.get("code"), positionId));
        }

        if (procedureId != null && !procedureId.isEmpty()) {
            SetJoin<EmployeeEntity, ProcedureEntity> positionJoin = root.joinSet("procedures", JoinType.INNER);
            predicate = builder.and(predicate, builder.equal(positionJoin.get("code"), procedureId));
        }

        predicate = builder.and(predicate, builder.equal(root.get("deleted"), false));
        return predicate;
    }

    default void applySorting(CriteriaBuilder builder, CriteriaQuery<EmployeeEntity> criteriaQuery, Root<EmployeeEntity> root, String sortBy, String sortType) {
        Expression<?> orderByExpression;
        if (sortBy == null || sortBy.isEmpty() || sortType == null) {
            Expression<String> firstName = root.get("name");
            Expression<String> lastName = root.get("surname");
            Expression<String> fullName = builder.concat(firstName, " ");
            orderByExpression = builder.concat(fullName, lastName);
            criteriaQuery.orderBy(builder.asc(orderByExpression));
        } else {
            switch (sortBy) {
                case SORT_USERS_BY_LOGIN -> orderByExpression = root.get("email");
                case SORT_USERS_BY_HIRE_DATE -> orderByExpression = root.get("hireDate");
                default -> {
                    Expression<String> firstName = root.get("name");
                    Expression<String> lastName = root.get("surname");
                    Expression<String> fullName = builder.concat(firstName, " ");
                    orderByExpression = builder.concat(fullName, lastName);
                }
            }
            criteriaQuery.orderBy(sortType.equals(SORT_TYPE_ASC) ? builder.asc(orderByExpression) : builder.desc(orderByExpression));
        }
    }




//    default void applyPagination(TypedQuery<EmployeeEntity> query, String page, String pageSize) {
//        int offset = Optional.ofNullable(page)
//                .filter(p -> !p.isEmpty())
//                .map(Integer::parseInt)
//                .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
//                .orElse(0);
//        query.setFirstResult(offset);
//        query.setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));
//    }

}
