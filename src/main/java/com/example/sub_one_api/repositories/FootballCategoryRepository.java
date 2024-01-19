package com.example.sub_one_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sub_one_api.models.FootballCategoryModel;

public interface FootballCategoryRepository extends JpaRepository<FootballCategoryModel, String> {}
