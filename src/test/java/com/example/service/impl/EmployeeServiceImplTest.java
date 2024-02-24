package com.example.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.repository.EmployeeRepository;
import com.example.repository.UserRoleRepository;
import com.example.repository.CountryRepository;
import com.example.service.mapper.user.employee.EmployeeMapper;
import com.example.validation.UserValidator;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private UserValidator validator;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetAll() {
//        // Настройка
//        when(userRepository.findAll(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(List.of(new EmployeeEntity()));
//        when(employeeMapper.toDto(any(EmployeeEntity.class))).thenReturn(new EmployeeDto());
//
//        // Выполнение
//        List<EmployeeDto> result = employeeService.getAll("id", "asc", "1", "test", "0", "10");
//
//        // Проверка
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        verify(userRepository, times(1)).findAll(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
//    }

//    @Test
//    public void testAddValidEmployee() {
//        // Настройка
//        when(validator.validate(anyString(), anyString(), anyString(), anyString(), any(LocalDate.class))).thenReturn(true);
//        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(userRoleRepository.findByName(anyString())).thenReturn(Optional.of(new UserRoleEntity()));
//
//        // Попытка добавить валидного сотрудника
//        assertDoesNotThrow(() -> employeeService.add(new EmployeeDto()));
//
//        // Проверка, что метод save был вызван
//        verify(userRepository, times(1)).save(any(EmployeeEntity.class));
//    }

    @Test
    public void testDeleteById() {
        // Выполнение и Проверка
        assertDoesNotThrow(() -> employeeService.deleteById(1L));
        verify(userRepository, times(1)).deleteById(anyLong());
    }

}