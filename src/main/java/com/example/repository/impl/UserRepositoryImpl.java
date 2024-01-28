package com.example.repository.impl;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    @Override
    public List<UserEntity> findAll() {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<UserEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return userDao.findAll( search, countryId, sortBy, sortType, page, pageSize);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public UserEntity getById(Long id) {
        try {
            return userDao.getById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(UserEntity user) {
        try {
            userDao.save(user);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            userDao.delete(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            final String filterAndSearchSql = userDao.getFilterAndSearchHql(countryId, search);
            return userDao.getTotalResult(filterAndSearchSql);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        try {
            return userDao.findByLogin(login);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
