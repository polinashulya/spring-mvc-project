package com.example.service;

import com.example.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize);

    void add(EmployeeDto employeeDto);

    void deleteById(Long employeeId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
