package com.example.repository;

import com.example.entity.ProcedureEntity;

import java.util.List;
import java.util.Optional;

public interface ProcedureRepository {

    List<ProcedureEntity> findAll();

    Optional<ProcedureEntity> findById(Long id);

}
