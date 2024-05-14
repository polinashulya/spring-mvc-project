package com.example.dao;

import com.example.entity.ClientEntity;
import com.example.entity.DeletionStatus;
import com.example.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao {

//    List<ClientEntity> findAll( String  search, String countyId, String sortBy, String sortType, String page, String pageSize);

//    Optional<ClientEntity> findById(Long id);

    UserEntity getByEmail(String email);

    Optional<UserEntity> findByEmail(String email);


}
