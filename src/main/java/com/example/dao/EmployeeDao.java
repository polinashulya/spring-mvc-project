package com.example.dao;

import com.example.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

    List<EmployeeEntity> findAll();

    List<EmployeeEntity> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    EmployeeEntity getById(Long id);

    Optional<EmployeeEntity> findById(Long id);

    void save(EmployeeEntity employee);

    void delete(Long id);

    EmployeeEntity getByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);

    String getFilterAndSearchHql(String countryId, String search);

    String getSortingHql(String sortBy, String sortType);

    int getTotalResult(String filterAndSearchsql);

}
