package com.example.service.impl;

import com.example.entity.EmployeePositionEntity;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.EmployeePositionRepository;
import com.example.service.EmployeePositionService;
import com.example.service.dto.EmployeePositionDto;
import com.example.service.mapper.EmployeePositionMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {
    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);

    private final EmployeePositionRepository employeePositionRepository;

    private final EmployeePositionMapper employeePositionMapper;

    @Override
    public List<EmployeePositionDto> findAll() {
        try {
            List<EmployeePositionEntity> entities = employeePositionRepository.findAll();
            return employeePositionMapper.toDtoList(entities);
        } catch (DAOException e) {
            logger.error("Error while getting all positions: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public EmployeePositionDto findById(String id) {
        try {
            return employeePositionRepository.findById(Long.parseLong(id))
                    .map(employeePositionMapper::toDto)
                    .orElse(null);
        } catch (NumberFormatException e) {
            logger.error("Error parsing position ID: {}", e.getMessage(), e);
            throw new ServiceException("Invalid position ID format", e);
        } catch (DAOException e) {
            logger.error("Error while finding position by ID: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
