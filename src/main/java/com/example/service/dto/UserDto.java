package com.example.service.dto;

import com.example.service.dto.core.AbstractBaseDto;
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

    //    @NotBlank(message = "Email is mandatory")
//    @Email(message = "Email must be valid")
    private String email;

    private String phoneNumber;

    //   @NotBlank(message = "Password is mandatory")
    private String password;

    //   @NotNull(message = "Role is mandatory")
    private UserRoleDto role;

    //   @NotBlank(message = "Name is mandatory")
    private String name;

    //  @NotBlank(message = "Surname is mandatory")
    private String surname;

    private CountryDto country;

    private String countryId;

    private String birthDate;

    private boolean banned;

    private boolean deleted;

}

