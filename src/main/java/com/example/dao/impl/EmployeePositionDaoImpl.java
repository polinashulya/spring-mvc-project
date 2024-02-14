package com.example.dao.impl;

import com.example.dao.EmployeePositionDao;
import com.example.entity.EmployeePositionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeePositionDaoImpl extends AbstractDaoImpl<EmployeePositionEntity> implements EmployeePositionDao {

    private static final String GET_POSITION_BY_ID = "FROM EmployeePositionEntity e WHERE e.id = :id";
    private static final String FIND_ALL_POSITIONS = "FROM EmployeePositionEntity";

    @Override
    public List<EmployeePositionEntity> findAll() {
        return findAll(FIND_ALL_POSITIONS);
    }

    @Override
    public Optional<EmployeePositionEntity> findById(Long id) {
        return Optional.ofNullable(
                getById(GET_POSITION_BY_ID, id)
        );
    }
}
