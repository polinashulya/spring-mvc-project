package com.example.service.impl;

import com.example.entity.ClientEntity;
import com.example.entity.UserRoleEntity;
import com.example.entity.UserRoles;
import com.example.exception.DAOException;
import com.example.exception.ServiceException;
import com.example.exception.UserAlreadyExistException;
import com.example.repository.ClientRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import com.example.service.UserService;
import com.example.service.dto.UserDto;
import com.example.service.dto.UserSignUpDTO;
import com.example.service.mapper.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ClientRepository clientRepository;

    private final UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public String signUpUser(UserSignUpDTO userSignUpDTO) {

        if (isEmailExists(userSignUpDTO.getEmail())) {
            throw new UserAlreadyExistException("There is already an account with this email " + userSignUpDTO.getEmail());
        }

        UserRoleEntity clientRole = userRoleRepository.findByName(UserRoles.CLIENT.name())
                .orElseThrow(() -> new ServiceException("Default role 'CLIENT' not found"));

        String encodedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());

        ClientEntity client = ClientEntity.builder()
                .email(userSignUpDTO.getEmail())
                .password(encodedPassword) // Consider encrypting this password
                .name(userSignUpDTO.getName())
                .surname(userSignUpDTO.getSurname())
                .phoneNumber(userSignUpDTO.getPhoneNumber())
                .registrationDate(LocalDate.now())
                .userRoles(Set.of(clientRole))
                .build();

        clientRepository.save(client);

//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//        confirmationTokenRepository.save(confirmationToken);
//
//        try {
//              emailService.sendConfirmAccountEmail(user.getEmail(), confirmationToken.getConfirmationToken());
//        } catch (Exception ex) {
//             throw new InvalidEmailException("Вы указали не верный Email! Такого не существует");
//        }

        return client.getEmail();
    }

    private boolean isEmailExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

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
