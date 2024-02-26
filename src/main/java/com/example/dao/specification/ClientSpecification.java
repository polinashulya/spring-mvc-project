package com.example.dao.specification;

import com.example.entity.ClientEntity;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

public interface ClientSpecification extends AbstractSpecification<ClientEntity>{

    String SORT_TYPE_ASC = "ASC";
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
                            builder.like(root.get("surname"), "%" + search + "%"),
                            builder.like(root.get("phoneNumber"), "%" + search + "%")
                    )
            );
        }
        predicate = builder.and(predicate, builder.equal(root.get("deleted"), false));
        return predicate;
    }

     default void applySorting(CriteriaBuilder builder, CriteriaQuery<ClientEntity> criteriaQuery, Root<ClientEntity> root, String sortBy, String sortType) {
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
                case SORT_USERS_BY_BIRTH_DATE -> orderByExpression = root.get("hireDate");
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

}
