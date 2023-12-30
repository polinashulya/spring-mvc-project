package com.example.service;

import com.example.entity.UserEntity;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    List<UserEntity> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    void add(UserEntity user);

    void deleteById(Long userId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
