package com.example.dao.specification;

import jakarta.persistence.TypedQuery;

import java.util.Optional;

public interface AbstractSpecification<T> {

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
