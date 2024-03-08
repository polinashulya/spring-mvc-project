package com.example.repository;

import com.example.entity.DeletionStatus;
import com.example.entity.ClientEntity;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<ClientEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    Optional<ClientEntity> findByEmail(String email);

    Optional<ClientEntity> findById(Long id);

    void save(ClientEntity client);

    DeletionStatus deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);

}
