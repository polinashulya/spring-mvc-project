package com.example.repository;

import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    User getById(Long id);

    Optional<User> findByLogin(String login);

    void save(User user);

    void deleteById(Long id);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);

}
