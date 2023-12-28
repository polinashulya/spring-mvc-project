package com.example.dao;

import com.example.entity.UserEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface UserDao {

    List<UserEntity> findAll();

    List<UserEntity> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

    UserEntity getById(Long id);

    Optional<UserEntity> findById(Long id);

    void save(UserEntity user);

    void delete(Long id);

    UserEntity getByLogin(String login);

    Optional<UserEntity> findByLogin(String login);

    String getFilterAndSearchHql(String countryId, String search);

    String getSortingHql(String sortBy, String sortType);

    int getTotalResult(String filterAndSearchsql);

}
