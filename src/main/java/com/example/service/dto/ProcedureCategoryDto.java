package com.example.service.dto;

import com.example.service.dto.core.AbstractCoreDto;
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
public class ProcedureCategoryDto  extends AbstractCoreDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

}
