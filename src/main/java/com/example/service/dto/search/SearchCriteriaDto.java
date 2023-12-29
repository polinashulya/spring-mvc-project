package com.example.service.dto.search;

import com.example.service.dto.core.AbstractBaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SearchCriteriaDto extends AbstractBaseDto {

    private String sortBy;

    private String sortType;

    private String countryId;

    private String search;

    private String page;

    private String pageSize;

}