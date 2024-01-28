package com.example.repository.impl;

import com.example.dao.UserRoleDao;
import com.example.entity.UserRoleEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRoleRepositoryImpl implements UserRoleRepository {

    private final UserRoleDao userRoleDao;

    @Override
    public Optional<UserRoleEntity> findByName(String name) {
        try {
            return userRoleDao.findByName(name);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}