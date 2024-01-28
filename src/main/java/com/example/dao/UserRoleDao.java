package com.example.dao;

import com.example.entity.CountryEntity;
import com.example.entity.UserRoleEntity;

import java.util.Optional;

public interface UserRoleDao {

    UserRoleEntity getByName(String name);

    Optional<UserRoleEntity> findByName(String name);

}
