package com.example.sub_one_api.dtos;

import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PlayerRecordDto(
  @NotBlank(message = "Name is required") String name, 
  @NotBlank(message = "Genre is required") String genre,
  String nickname,
  String birthdate,
  String address,
  String country_code,
  Long phone_number,
  @Valid Set<GuardianRecordDto> guardians
) {}
