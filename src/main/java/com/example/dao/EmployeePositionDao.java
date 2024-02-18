package com.example.dao;

import com.example.entity.EmployeePositionEntity;
import com.example.entity.ProcedureEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeePositionDao extends AbstractDao<EmployeePositionEntity> {

    List<EmployeePositionEntity> findAll();

    Optional<EmployeePositionEntity> findById(Long id);

}
