package com.example.repository.impl;

import com.example.dao.ClientDao;
import com.example.dao.impl.DeletionStatus;
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
    public List<ClientEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return clientDao.findAll( search, countryId, sortBy, sortType, page, pageSize);
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
    public DeletionStatus deleteById(Long id) {
        try {
            return clientDao.softDelete(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return clientDao.getTotalResult(search,countryId);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<ClientEntity> findByEmail(String email) {
        try {
            return clientDao.findByEmail(email);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<ClientEntity> findById(Long id) {
        try {
            return clientDao.findById(id);
        } catch (DAOException e) {
            throw new RepositoryException(e);
        }
    }
}