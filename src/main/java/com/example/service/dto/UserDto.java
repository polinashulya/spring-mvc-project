package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;
import com.example.service.dto.core.AbstractCoreDto;
import jakarta.persistence.Column;
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
public class UserDto extends AbstractBaseDto {

    private Long id;

    private String login;

    private String password;

    private String name;

    private String surname;

    private CountryDto country;

    private String countryId;

    private String birthDate;

    private boolean banned;

    private boolean deleted;

}

