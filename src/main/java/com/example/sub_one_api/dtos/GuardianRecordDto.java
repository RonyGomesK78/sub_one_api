package com.example.sub_one_api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record GuardianRecordDto(
  @NotBlank String name,
  Long phone_number,
  String country_code,
  @Email(message = "Email must be valid") String email
) {}
