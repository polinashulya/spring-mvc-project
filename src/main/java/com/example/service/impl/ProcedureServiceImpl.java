package com.example.service.impl;

import com.example.entity.ProcedureEntity;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.ProcedureRepository;
import com.example.service.ProcedureService;
import com.example.service.dto.ProcedureDto;
import com.example.service.mapper.ProcedureMapper;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {
    private static final Logger logger = LogManager.getLogger(CountryServiceImpl.class);

    private final ProcedureRepository procedureRepository;

    private final ProcedureMapper procedureMapper;

    @Override
    public List<ProcedureDto> findAll() {
        try {
            List<ProcedureEntity> entities = procedureRepository.findAll();
            return procedureMapper.toDtoList(entities);
        } catch (DAOException e) {
            logger.error("Error while getting all procedures: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ProcedureDto findById(String id) {
        try {
            return procedureRepository.findById(Long.parseLong(id))
                    .map(procedureMapper::toDto)
                    .orElse(null);
        } catch (NumberFormatException e) {
            logger.error("Error parsing procedure ID: {}", e.getMessage(), e);
            throw new ServiceException("Invalid procedure ID format", e);
        } catch (DAOException e) {
            logger.error("Error while finding procedure by ID: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }
}
