package com.example.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO <T>{
    List<T> findAll();

    List<T> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    T getById(Long id);

    Optional<T> findById(Class<T> type, Long id);

    void save(T user);


    void delete(Long id);

    T getByEmail(String email);

    Optional<T> findByEmail(String email);

    String getFilterAndSearchHql(String countryId, String search);

    String getSortingHql(String sortBy, String sortType);

    int getTotalResult(String filterAndSearchsql);

}

