package com.example.repository;

import com.example.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserEntity> findAll();

    List<UserEntity> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    UserEntity getById(Long id);

    Optional<UserEntity> findByEmail(String email);

    void save(UserEntity user);

    void deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);

}
