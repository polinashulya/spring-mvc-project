package com.example.service.dto;

import com.example.service.dto.core.AbstractCoreDto;
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
public class ProcedureDto  extends AbstractCoreDto {

    private String name;

    private String duration;

    private String description;

    private BigDecimal price;

    private ProcedureCategoryDto procedureCategory;

}
