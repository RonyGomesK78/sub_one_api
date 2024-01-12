package com.example.sub_one_api.records;

import java.util.UUID;

public record PlayerRecord(UUID id, String name, String age, String genre, String average, String position, GuardianRecord[] guardians) { }
