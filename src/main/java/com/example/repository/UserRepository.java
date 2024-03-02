package com.example.repository;

import com.example.entity.ClientEntity;
import com.example.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByEmail(String email);

}
