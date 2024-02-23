package com.example.service.dto;

import com.example.service.dto.core.AbstractCoreDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDto extends AbstractCoreDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email must be valid")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "Password is mandatory")
    private String password;

  //  @NotNull(message = "Role is mandatory")
    private UserRoleDto role;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    private LocalDate birthDate;

    private boolean banned;

    private boolean deleted;

}

