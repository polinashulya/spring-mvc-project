package com.example.service.dto;

import com.example.validation.annotatoin.ValidPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignUpDTO {

    @NotNull
    @Email(message = "Email must be valid")
    private String email;

    private String password;

    private UserRoleDto userRole;

    @NotNull
    private String matchingPassword;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 20, message = "The name is mandatory and must be at least 2 characters and no more than 20 characters.")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 2, max = 20, message = "The surname is mandatory and must be at least 2 characters and no more than 20 characters.")
    private String surname;

    @NotNull
    @ValidPhone
    private String phoneNumber;

}
