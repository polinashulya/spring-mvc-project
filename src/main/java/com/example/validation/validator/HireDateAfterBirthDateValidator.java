package com.example.validation.validator;

import com.example.service.dto.EmployeeDto;
import com.example.validation.annotatoin.HireDateAfterBirthDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HireDateAfterBirthDateValidator implements ConstraintValidator<HireDateAfterBirthDate, EmployeeDto> {

    @Override
    public boolean isValid(EmployeeDto dto, ConstraintValidatorContext context) {
        if (dto.getBirthDate() == null || dto.getHireDate() == null) {
            return true;
        }
        return dto.getHireDate().isAfter(dto.getBirthDate());
    }
}
