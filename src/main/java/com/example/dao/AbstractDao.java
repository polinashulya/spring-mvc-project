package com.example.dao;

import com.example.entity.core.AbstractBaseEntity;
import jakarta.persistence.TypedQuery;

import java.util.List;

public interface AbstractDao<E extends AbstractBaseEntity> {
    E getById(String sql, Long id);

    List<E> findAll(String sql);

    <E> void save(E clazz);

    void applyPagination(TypedQuery<?> query, String page, String pageSize);
}
