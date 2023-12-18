package com.example.repository.impl;

import com.example.dao.UserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.exception.ServiceException;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

//    public UserRepositoryImpl() {
//        userDao = new UserDaoImpl();
//    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public List<User> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            final String filterAndSearchSql = userDao.getFilterAndSearchSql(countryId, search);
            final String sortSql = userDao.getSortingSql(sortBy, sortType);
            return userDao.findAll(filterAndSearchSql, sortSql, page, pageSize);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public User getById(Long id) {
        try {
            return userDao.getById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(User user) {
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
            final String filterAndSearchSql = userDao.getFilterAndSearchSql(countryId, search);
            return userDao.getTotalResult(filterAndSearchSql);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            return userDao.findByLogin(login);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}
