package com.example.sub_one_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sub_one_api.models.FootballPositionModel;

public interface FootballPositionRepository extends JpaRepository<FootballPositionModel, String> {}
