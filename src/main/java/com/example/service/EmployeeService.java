package com.example.service;

import com.example.service.dto.EmployeeDto;
import com.example.service.dto.search.UserSearchCriteriaDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAll(UserSearchCriteriaDto employeeSearchCriteriaDto);

    void add(EmployeeDto employeeDto);

    void deleteById(Long employeeId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
