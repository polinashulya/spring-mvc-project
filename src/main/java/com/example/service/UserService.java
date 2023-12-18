package com.example.service;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface UserService {

    User getById(String userId);

    List<User> getAll();

    List<User> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    void add(User user);

    void deleteById(Long userId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
