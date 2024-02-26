package com.example.service;

import com.example.entity.DeletionStatus;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.EmployeeSearchCriteriaDto;

public interface EmployeeService {
    PageableDto<EmployeeDto> getAll(EmployeeSearchCriteriaDto employeeSearchCriteriaDto);

    void save(EmployeeDto employeeDto);

    DeletionStatus deleteById(Long employeeId);

    int getTotalResult(String sortBy, String sortType, String countryId, String search, String positionId, String procedureId);
}
