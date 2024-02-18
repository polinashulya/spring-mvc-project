package com.example.service;

import com.example.dao.impl.DeletionStatus;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.UserSearchCriteriaDto;

public interface EmployeeService {
    PageableDto<EmployeeDto> getAll(UserSearchCriteriaDto employeeSearchCriteriaDto);

    void add(EmployeeDto employeeDto);

    DeletionStatus deleteById(Long employeeId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search);
}
