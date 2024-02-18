package com.example.dao;

import com.example.entity.CountryEntity;

import java.util.List;
import java.util.Optional;

public interface CountryDao extends AbstractDao<CountryEntity> {
    List<CountryEntity> findAll();

    Optional<CountryEntity> findById(Long id);

}
