package com.example.repository.impl;

import com.example.entity.ClientEntity;
import com.example.entity.EmployeeEntity;
import com.example.entity.UserEntity;
import com.example.repository.ClientRepository;
import com.example.repository.EmployeeRepository;
import com.example.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        Optional<UserEntity> clientUser = clientRepository.findByEmail(email)
                .map(this::mapToUserEntity); // Converts ClientEntity to UserEntity
        if (clientUser.isPresent()) {
            return clientUser;
        }

        return employeeRepository.findByEmail(email)
                .map(this::mapToUserEntity); // Converts EmployeeEntity to UserEntity
    }

    private UserEntity mapToUserEntity(ClientEntity clientEntity) {
        // Create a new instance of UserEntity
        UserEntity user = new UserEntity();
        // Map common attributes from ClientEntity to UserEntity
        user.setCountry(clientEntity.getCountry());
        user.setEmail(clientEntity.getEmail());
        user.setPhoneNumber(clientEntity.getPhoneNumber());
        user.setPassword(clientEntity.getPassword());
        user.setName(clientEntity.getName());
        user.setSurname(clientEntity.getSurname());
        user.setBirthDate(clientEntity.getBirthDate());
        user.setRegistrationDate(clientEntity.getRegistrationDate());
        user.setBanned(clientEntity.isBanned());
        user.setDeleted(clientEntity.isDeleted());
        user.setUserRoles(clientEntity.getUserRoles());
        // You can continue mapping any additional attributes that are common
        // Note: Since ClientEntity extends UserEntity, you might just return the original clientEntity
        // after ensuring all desired properties are correctly set
        return user;
    }

    private UserEntity mapToUserEntity(EmployeeEntity employeeEntity) {
        // Create a new instance of UserEntity
        UserEntity user = new UserEntity();
        // Map common attributes from EmployeeEntity to UserEntity
        user.setCountry(employeeEntity.getCountry());
        user.setEmail(employeeEntity.getEmail());
        user.setPhoneNumber(employeeEntity.getPhoneNumber());
        user.setPassword(employeeEntity.getPassword());
        user.setName(employeeEntity.getName());
        user.setSurname(employeeEntity.getSurname());
        user.setBirthDate(employeeEntity.getBirthDate());
        user.setRegistrationDate(employeeEntity.getRegistrationDate());
        user.setBanned(employeeEntity.isBanned());
        user.setDeleted(employeeEntity.isDeleted());
        user.setUserRoles(employeeEntity.getUserRoles());
        // You can continue mapping any additional attributes that are common
        // Note: Since EmployeeEntity extends UserEntity, you might just return the original employeeEntity
        // after ensuring all desired properties are correctly set
        return user;
    }
}