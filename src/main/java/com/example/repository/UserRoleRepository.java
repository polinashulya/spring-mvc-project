package com.example.repository;

import com.example.entity.UserRoleEntity;

import java.util.Optional;

public interface UserRoleRepository {

    Optional<UserRoleEntity> findByName(String name);

}
