package com.example.dao;

import com.example.entity.core.AbstractBaseEntity;

import java.util.List;

public interface AbstractDao<E extends AbstractBaseEntity> {
    E getById(String sql, Long id);

  //  List<E> findAll();
}
