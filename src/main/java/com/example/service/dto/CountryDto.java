package com.example.service.dto;

import com.example.service.dto.core.AbstractCoreDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CountryDto extends AbstractCoreDto {

    private String name;

}
