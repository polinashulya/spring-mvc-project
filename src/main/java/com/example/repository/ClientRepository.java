package com.example.repository;

import com.example.entity.ClientEntity;
import com.example.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<ClientEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

//    ClientEntity getById(Long id);

    Optional<ClientEntity> findByEmail(String email);

    void save(ClientEntity user);

    void deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);

}
