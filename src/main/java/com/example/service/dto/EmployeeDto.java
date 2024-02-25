package com.example.service.dto;

import com.example.validation.annotatoin.HireDateAfterBirthDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@HireDateAfterBirthDate
public class EmployeeDto extends UserDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    private List<EmployeePositionDto> positions = new ArrayList<>();

    private List<ProcedureDto> procedures;

}
