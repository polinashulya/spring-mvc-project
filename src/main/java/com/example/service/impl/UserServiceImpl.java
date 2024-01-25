package com.example.service.impl;

import com.example.entity.UserEntity;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.service.dto.UserDto;
import com.example.service.mapper.UserMapper;
import com.example.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CountryRepository countryRepository;

    private final UserValidator validator;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        try {
            List<UserEntity> entities = userRepository.findAll();
            return entities.stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            logger.error("Error while getting all users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<UserEntity> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return userRepository.findAll(sortBy, sortType, countryId, search, page, pageSize);
        } catch (DAOException e) {
            logger.error("Error while getting all users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(UserDto userDto) {

        if (!validator.validate(userDto.getLogin(), userDto.getPassword(),
                userDto.getName(), userDto.getSurname(), LocalDate.parse(userDto.getBirthDate()))) {
            throw new ServiceException("Information is not valid!");
        }

        if (userDto.getCountryId() == null || countryRepository.findById(Long.valueOf(userDto.getCountryId())).isEmpty()) {
            throw new ServiceException("Country is null or did not find!");
        }

        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new ServiceException("Login is already in use!");
        }

        UserEntity user = userMapper.toEntity(userDto);

        try {
            userRepository.save(user);
        } catch (DAOException e) {
            logger.error("Error while adding a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }


    @Override
    public void deleteById(Long userId) {
        try {
            if (userId == null) {
                throw new ServiceException("User ID is required.");
            }
            userRepository.deleteById(userId);
        } catch (DAOException e) {
            logger.error("Error while deleting a user: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int getTotalResult(String sortBy, String sortType, String countryId, String search) {
        try {
            return userRepository.getTotalResult(sortBy, sortType, countryId, search);
        } catch (DAOException e) {
            logger.error("Error while getting a total result of users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

}
