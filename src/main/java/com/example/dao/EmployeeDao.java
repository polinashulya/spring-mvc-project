package com.example.dao;

import com.example.dao.impl.DeletionStatus;
import com.example.entity.ClientEntity;
import com.example.entity.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {


    List<EmployeeEntity> findAll(String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    Optional<EmployeeEntity> findById(Long id);

    void save(EmployeeEntity client);

    DeletionStatus softDelete(Long id);

    EmployeeEntity getByEmail(String email);

    Optional<EmployeeEntity> findByEmail(String email);

    int getTotalResult(String search, String countryId);


}
