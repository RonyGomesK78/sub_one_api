package com.example.sub_one_api;

public record Player(long id, String name, String age, String genre, String average, String position, Guardian[] guardians) { }
