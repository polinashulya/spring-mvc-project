package com.example.dao;

import com.example.entity.DeletionStatus;
import com.example.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    List<ClientEntity> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    Optional<ClientEntity> findById(Long id);

    DeletionStatus softDelete(Long id);

    ClientEntity getByEmail(String email);

    Optional<ClientEntity> findByEmail(String email);

    int getTotalResult(String search, String countryId);

    void save(ClientEntity client);

}
