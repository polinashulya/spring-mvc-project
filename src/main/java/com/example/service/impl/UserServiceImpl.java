package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.CountryRepository;
import com.example.repository.UserRepository;
import com.example.repository.impl.CountryRepositoryImpl;
import com.example.repository.impl.UserRepositoryImpl;
import com.example.service.UserService;
import com.example.servlet.MainServlet;
import com.example.validator.UserValidator;
import com.example.validator.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final CountryRepository countryRepository;

    private final UserValidator validator;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
        this.countryRepository = new CountryRepositoryImpl();
        this.validator = new UserValidatorImpl();
    }

    @Override
    public User getById(String userId) {
        return null;
    }

    @Override
    public List<User> getAll(String sortBy, String sortType, String countryId, String search, String page, String pageSize) {
        try {
            return userRepository.findAll(sortBy, sortType, countryId, search, page, pageSize);
        } catch (DAOException e) {
            logger.error("Error while getting all users: {}", e.getMessage(), e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void add(User user) {
        if (!validator.validate(user.getLogin(), user.getPassword(),
                user.getFirstname(), user.getSurname(), user.getBirthDate())) {
            throw new ServiceException("Information is not valid!");
        }
        if (user.getCountry() == null || countryRepository.findById(user.getCountry().getId()).isEmpty()) {
            throw new ServiceException("Country is null or did not find!");
        }

        if ( userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new ServiceException("Login is already in use!");
        }

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
