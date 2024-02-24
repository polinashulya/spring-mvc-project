package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProcedureDto  extends AbstractBaseDto {

    private String code;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String duration;

    private String description;

    private BigDecimal price;

    private ProcedureCategoryDto procedureCategory;

}
