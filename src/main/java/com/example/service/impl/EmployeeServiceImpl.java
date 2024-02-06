package com.example.service.impl;

import com.example.entity.EmployeeEntity;
import com.example.entity.UserRoleEntity;
import com.example.entity.UserRoles;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.EmployeeService;
import com.example.service.dto.EmployeeDto;
import com.example.service.mapper.user.employee.EmployeeMapper;
import com.example.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final CountryRepository countryRepository;

    private final UserValidator validator;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            List<EmployeeEntity> entities = userRepository.findAll(sortBy, sortType, countryId, search, page, pageSize);
            return entities.stream()
                    .map(employeeMapper::toDto)
                    .collect(Collectors.toList());
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

        UserRoleEntity employeeRole = userRoleRepository.findByName(UserRoles.CLIENT.name())
                .orElseThrow(() -> new ServiceException("Default role 'CLIENT' not found"));

        employeeEntity.setUserRoles((Set<UserRoleEntity>) employeeRole);

        try {
            userRepository.save(employeeEntity);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while adding a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }


    @Override
    public void deleteById(Long userId) {
        try {
            if (userId == null) {
                throw new ServiceException("Employee ID is required.");
            }
            userRepository.deleteById(userId);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while deleting a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return userRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException | ServiceException e) {
            logger.error("Error while getting a total result of users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

}
