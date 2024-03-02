package com.example.service.impl;

import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.service.dto.UserDto;
import com.example.service.mapper.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto findByEmail(String email) {
        try {
            return userRepository.findByEmail(email)
                    .map(userMapper::toDto)
                    .orElse(null);
        } catch (NumberFormatException e) {
            throw new ServiceException("Invalid country ID format", e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
