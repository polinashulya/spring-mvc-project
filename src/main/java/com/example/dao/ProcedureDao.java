package com.example.dao;

import com.example.entity.EmployeePositionEntity;
import com.example.entity.ProcedureEntity;

import java.util.List;
import java.util.Optional;

public interface ProcedureDao {

    List<ProcedureEntity> findAll();

    Optional<ProcedureEntity> findById(Long id);

}
