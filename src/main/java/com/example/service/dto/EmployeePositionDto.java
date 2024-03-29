package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class EmployeePositionDto extends AbstractBaseDto {

    private String code;

    @NotBlank(message = "Name is mandatory")
    private String name;

}
