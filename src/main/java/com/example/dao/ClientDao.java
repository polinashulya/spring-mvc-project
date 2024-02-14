package com.example.dao;

import com.example.entity.ClientEntity;
import com.example.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    List<ClientEntity> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    Optional<ClientEntity> findById(Long id);

    void save(ClientEntity client);

    void delete(Long id);

    ClientEntity getByEmail(String email);

    Optional<ClientEntity> findByEmail(String email);

    String getFilterAndSearchHql(String countryId, String search);

    String getSortingHql(String sortBy, String sortType);

    int getTotalResult(String filterAndSearchsql);

}
