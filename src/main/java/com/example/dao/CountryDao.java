package com.example.dao;

import com.example.entity.CountryEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CountryDao {
    List<CountryEntity> findAll();

    CountryEntity getById(Long id);

    Optional<CountryEntity> findById(Long id);

    CountryEntity getByName(String name);

    Optional<CountryEntity> findByName(String name);

}
