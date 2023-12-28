package com.example.dao;

import com.example.entity.CountryEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<CountryEntity> findAll();

    CountryEntity getById(Serializable id);

    Optional<CountryEntity> findById(Serializable id);

}
