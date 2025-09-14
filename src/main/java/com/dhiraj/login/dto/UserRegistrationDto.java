package com.dhiraj.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    @NotBlank
    @Size(min=3, max=50)
    private String username;

    @NotBlank
    @Size(min=6, max=100)
    private String password;

    @NotBlank
    private String confirmPassword;


}
