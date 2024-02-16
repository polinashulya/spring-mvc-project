package com.example.dao.specification;

import com.example.entity.ClientEntity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.Optional;

public interface ClientSpecification {

    String SORT_TYPE_ASC = "ASC";
    String SORT_USERS_BY_ID = "byId";
    String SORT_USERS_BY_SURNAME = "bySurname";
    String SORT_USERS_BY_LOGIN = "byEmail";
    String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    default CriteriaQuery<ClientEntity> buildCriteriaQuery(Session session, String search, String countryId, String sortBy, String sortType) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);

        Predicate predicate = buildPredicate(builder, root, search, countryId);
        criteriaQuery.where(predicate);

        applySorting(builder, criteriaQuery, root, sortBy, sortType);

        return criteriaQuery;
    }

    default Predicate buildPredicate(CriteriaBuilder builder, Root<ClientEntity> root, String search, String countryId) {
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
            Expression<?> orderByExpression = switch (sortBy) {
                case SORT_USERS_BY_LOGIN -> root.get("email");
                case SORT_USERS_BY_SURNAME -> root.get("surname");
                case SORT_USERS_BY_BIRTH_DATE -> root.get("birthDate");
                default -> root.get("id");
            };
            criteriaQuery.orderBy(sortType.equals(SORT_TYPE_ASC) ? builder.asc(orderByExpression) : builder.desc(orderByExpression));
        }
    }

    default void applyPagination(TypedQuery<ClientEntity> query, String page, String pageSize) {
        int offset = Optional.ofNullable(page)
                .filter(p -> !p.isEmpty())
                .map(Integer::parseInt)
                .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                .orElse(0);
        query.setFirstResult(offset);
        query.setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));
    }

}