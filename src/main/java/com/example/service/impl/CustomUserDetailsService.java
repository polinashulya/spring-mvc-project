package com.example.service.impl;

import com.example.entity.ClientEntity;
import com.example.entity.EmployeeEntity;
import com.example.repository.ClientRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    private final EmployeeRepository employeeRepository;


    @Autowired
    public CustomUserDetailsService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ClientEntity> clientOpt = clientRepository.findByEmail(username);
        if (clientOpt.isPresent()) {
            ClientEntity client = clientOpt.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(client.getEmail())
                    .password(client.getPassword())
                    .roles("CLIENT")
                    .build();
        }

        Optional<EmployeeEntity> employeeOpt = employeeRepository.findByEmail(username);
        if (employeeOpt.isPresent()) {
            EmployeeEntity employee = employeeOpt.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(employee.getEmail())
                    .password(employee.getPassword())
                    .roles("EMPLOYEE")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}



