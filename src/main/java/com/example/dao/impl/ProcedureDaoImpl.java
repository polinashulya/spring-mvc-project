package com.example.dao.impl;

import com.example.dao.ProcedureDao;
import com.example.entity.ProcedureEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProcedureDaoImpl extends AbstractDaoImpl<ProcedureEntity> implements ProcedureDao {

    private static final String GET_PROCEDURES_BY_ID = "FROM ProcedureEntity e WHERE e.id = :id";
    private static final String FIND_ALL_PROCEDURES = "FROM ProcedureEntity";

    @Override
    public List<ProcedureEntity> findAll() {
        return findAll(FIND_ALL_PROCEDURES);
    }

    @Override
    public Optional<ProcedureEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_PROCEDURES_BY_ID, id)
        );
    }
}
