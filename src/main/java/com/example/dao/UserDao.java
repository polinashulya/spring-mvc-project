package com.example.dao;

import com.example.entity.User;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;


public interface UserDao {

    List<User> findAll();

    List<User> findAll(String filterAndSearchsql,String sortSql, String page, String pageSize);

    User getById(Long id);

    Optional<User> findById(Long id);

    void save(User user);

    void delete(Long id);

    User getByLogin(String login);

    Optional<User> findByLogin(String login);

    String getFilterAndSearchSql(String countryId, String search);

    String getSortingSql(String sortBy, String sortType);

    int getTotalResult(String filterAndSearchsql);

}
