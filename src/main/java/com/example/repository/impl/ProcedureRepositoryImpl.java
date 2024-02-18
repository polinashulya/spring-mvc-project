package com.example.repository.impl;

import com.example.dao.ProcedureDao;
import com.example.entity.ProcedureEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.ProcedureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProcedureRepositoryImpl implements ProcedureRepository {

    private final ProcedureDao procedureDao;

    @Override
    public List<ProcedureEntity> findAll() {
        return procedureDao.findAll();
    }

    @Override
    public Optional<ProcedureEntity> findById(Long id) {
        try {
            return procedureDao.findById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
