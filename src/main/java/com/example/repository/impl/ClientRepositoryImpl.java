package com.example.repository.impl;

import com.example.dao.ClientDao;
import com.example.entity.ClientEntity;
import com.example.exception.DAOException;
import com.example.exception.RepositoryException;
import com.example.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientDao clientDao;

    @Override
    public List<ClientEntity> findAll() {
        try {
            return clientDao.findAll();
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<ClientEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return clientDao.findAll( search, countryId, sortBy, sortType, page, pageSize);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public ClientEntity getById(Long id) {
        try {
            return clientDao.getById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(ClientEntity client) {
        try {
            clientDao.save(client);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            clientDao.delete(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            final String filterAndSearchSql = clientDao.getFilterAndSearchHql(countryId, search);
            return clientDao.getTotalResult(filterAndSearchSql);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<ClientEntity> findByLogin(String login) {
        try {
            return clientDao.findByLogin(login);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}