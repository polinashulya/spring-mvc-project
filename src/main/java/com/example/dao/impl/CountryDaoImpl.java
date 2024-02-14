package com.example.dao.impl;

import com.example.dao.CountryDao;
import com.example.entity.CountryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CountryDaoImpl extends AbstractDaoImpl<CountryEntity> implements CountryDao {

    private static final String GET_COUNTRY_BY_ID = "FROM CountryEntity c WHERE c.id = :id";
    private static final String FIND_ALL_COUNTRIES = "FROM CountryEntity";

    @Override
    public List<CountryEntity> findAll() {
        return findAll(FIND_ALL_COUNTRIES);
    }

    @Override
    public Optional<CountryEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_COUNTRY_BY_ID, id)
        );
    }
}
