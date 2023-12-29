package com.example.service.dto.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserSearchCriteriaDto extends SearchCriteriaDto{

    private String countryId;

}
