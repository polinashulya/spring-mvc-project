package com.example.dao.specification;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.Optional;

public interface AbstractSpecification<T> {

    default CriteriaQuery<T> buildCriteriaQuery(Session session, Class<T> clazz, String search, String countryId, String sortBy, String sortType) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        Predicate predicate = buildPredicate(builder, root, search, countryId);
        criteriaQuery.where(predicate);
        //applySorting(builder, criteriaQuery, root, sortBy, sortType);

        return criteriaQuery;
    }

    default Predicate buildPredicate(CriteriaBuilder builder, Root<T> root, String search, String countryId) {
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

    default void applyPagination(TypedQuery<T> query, String page, String pageSize) {
        int offset = Optional.ofNullable(page)
                .filter(p -> !p.isEmpty())
                .map(Integer::parseInt)
                .map(p -> (p - 1) * Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5))
                .orElse(0);
        query.setFirstResult(offset);
        query.setMaxResults(Optional.ofNullable(pageSize).map(Integer::parseInt).orElse(5));
    }

}
