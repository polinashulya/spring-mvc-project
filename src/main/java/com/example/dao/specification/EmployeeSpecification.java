package com.example.dao.specification;

import com.example.entity.EmployeeEntity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.Optional;

public interface EmployeeSpecification {

    String SORT_TYPE_ASC = "ASC";
    String SORT_USERS_BY_SURNAME = "bySurname";
    String SORT_USERS_BY_LOGIN = "byEmail";
    String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    default CriteriaQuery<EmployeeEntity> buildCriteriaQuery(Session session, String search, String countryId, String sortBy, String sortType) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = builder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);
        root.fetch("positions", JoinType.LEFT);
        root.fetch("procedures", JoinType.LEFT);

        Predicate predicate = buildPredicate(builder, root, search, countryId);
        criteriaQuery.where(predicate);

        applySorting(builder, criteriaQuery, root, sortBy, sortType);

        return criteriaQuery;
    }

    default Predicate buildPredicate(CriteriaBuilder builder, Root<EmployeeEntity> root, String search, String countryId) {
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

    private void applySorting(CriteriaBuilder builder, CriteriaQuery<EmployeeEntity> criteriaQuery, Root<EmployeeEntity> root, String sortBy, String sortType) {
        if (sortBy != null && !sortBy.isEmpty()) {
            Expression<?> orderByExpression = switch (sortBy) {
                case SORT_USERS_BY_LOGIN -> root.get("email");
                case SORT_USERS_BY_SURNAME -> root.get("surname");
                case SORT_USERS_BY_BIRTH_DATE -> root.get("birthDate");
                default -> root.get("id");
            };
            criteriaQuery.orderBy(sortType.equals(SORT_TYPE_ASC) ? builder.asc(orderByExpression) : builder.desc(orderByExpression));
        }
    }

    default void applyPagination(TypedQuery<EmployeeEntity> query, String page, String pageSize) {
        int offset = Optional.ofNullable(page)
                .filter(p -> !p.isEmpty())
                .map(Integer::parseInt)
                .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                .orElse(0);
        query.setFirstResult(offset);
        query.setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));
    }

}
