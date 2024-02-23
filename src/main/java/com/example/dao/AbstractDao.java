package com.example.dao;

import com.example.dao.impl.DeletionStatus;
import com.example.entity.core.AbstractBaseEntity;

import java.util.List;

public interface AbstractDao<E extends AbstractBaseEntity> {
    E getById(String sql, Long id);

    List<E> findAll(String sql);

    void save(E clazz);

    DeletionStatus softDelete(String sql, Long id);

}
