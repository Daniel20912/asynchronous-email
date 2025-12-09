package com.danieloliveira.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserDto implements Serializable {

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    @Email(message = "The email format is incorrect")
    @NotBlank(message = "Email cannot be blank")
    private final String email;
}