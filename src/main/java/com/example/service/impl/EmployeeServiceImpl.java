package com.example.service.impl;

import com.example.dao.impl.DeletionStatus;
import com.example.entity.EmployeeEntity;
import com.example.entity.UserRoleEntity;
import com.example.entity.UserRoles;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.EmployeeRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.EmployeeService;
import com.example.service.dto.EmployeeDto;
import com.example.service.dto.PageableDto;
import com.example.service.dto.search.UserSearchCriteriaDto;
import com.example.service.mapper.user.employee.EmployeeMapper;
import com.example.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final UserValidator validator;

    private final EmployeeMapper employeeMapper;

    @Override
    public PageableDto<EmployeeDto> getAll(UserSearchCriteriaDto employeeSearchCriteriaDto) {
        try {
            List<EmployeeEntity> entities = userRepository.findAll(
                    employeeSearchCriteriaDto.getSortBy(),
                    employeeSearchCriteriaDto.getSortType(),
                    employeeSearchCriteriaDto.getCountryId(),
                    employeeSearchCriteriaDto.getSearch(),
                    employeeSearchCriteriaDto.getPage(),
                    employeeSearchCriteriaDto.getPageSize());

            int totalResult = getTotalResult(employeeSearchCriteriaDto.getSortBy(), employeeSearchCriteriaDto.getSortType(),
                    employeeSearchCriteriaDto.getCountryId(), employeeSearchCriteriaDto.getSearch());

            return PageableDto.<EmployeeDto>builder()
                    .elements(employeeMapper.toDtoList(entities))
                    .totalSize(totalResult)
                    .build();
        } catch (DAOException e) {
            logger.error("Error while getting all users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(EmployeeDto employeeDto) {

        if (!validator.validate(employeeDto.getEmail(), employeeDto.getPassword(),
                employeeDto.getName(), employeeDto.getSurname(), LocalDate.parse(employeeDto.getBirthDate()))) {
            throw new ServiceException("Information is not valid!");
        }

//        if (employeeDto.getCountry().getId() == null || countryRepository.findById(employeeDto.getCountry().getId()).isEmpty()) {
//            throw new ServiceException("Country is null or did not find!");
//        }

        if (userRepository.findByEmail(employeeDto.getEmail()).isPresent()) {
            throw new ServiceException("Email is already in use!");
        }

        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeDto);

        UserRoleEntity employeeRole = userRoleRepository.findByName(UserRoles.EMPLOYEE.name())
                .orElseThrow(() -> new ServiceException("Default role 'EMPLOYEE' not found"));

        Set<UserRoleEntity> roles = new HashSet<>();
        roles.add(employeeRole);

        employeeEntity.setRegistationDate(LocalDate.now());

        employeeEntity.setUserRoles(roles);

        try {
            userRepository.save(employeeEntity);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while adding a employee: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }


    @Override
    public DeletionStatus deleteById(Long employeeId) {
        try {
            if (employeeId == null) {
                throw new ServiceException("Employee ID is required.");
            }
            userRepository.deleteById(employeeId);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while deleting a employee: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
        return null;
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return userRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while getting a total result of employees: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

}
