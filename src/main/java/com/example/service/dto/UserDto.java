package com.example.service.dto;

import com.example.service.dto.core.AbstractCoreDto;
import com.example.validation.annotatoin.ValidAge;
import com.example.validation.annotatoin.ValidPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDto extends AbstractCoreDto {

    @NotNull
    @Email(message = "Email must be valid")
    private String email;

    @NotNull
    @ValidPhone
    private String phoneNumber;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 2, max = 20, message = "The password is mandatory and must be at least 2 characters and no more than 20 characters.")
    private String password;

    private UserRoleDto role;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 20, message = "The name is mandatory and must be at least 2 characters and no more than 20 characters.")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 20, message = "The surname is mandatory and must be at least 2 characters and no more than 20 characters.")
    private String surname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidAge(message = "You must be at least 16 years old", minAge = 16)
    private LocalDate birthDate;

    private boolean banned;

    private boolean deleted;

}

