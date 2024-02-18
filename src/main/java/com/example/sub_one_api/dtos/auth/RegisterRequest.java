package com.example.sub_one_api.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
    @NotBlank String firstname,
    @NotBlank String lastname,
    @Email @NotBlank String email,
    @NotBlank @Length(min = 7) String password,
    @NotBlank String role
) {}
