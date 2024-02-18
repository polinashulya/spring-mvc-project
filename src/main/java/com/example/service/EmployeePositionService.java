package com.example.service;

import com.example.service.dto.EmployeePositionDto;
import java.util.List;

public interface EmployeePositionService {

    List<EmployeePositionDto> findAll();

    EmployeePositionDto findById(String id);

}
