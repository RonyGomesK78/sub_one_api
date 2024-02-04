package com.example.sub_one_api.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.models.PlayerModel;;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
  @Query("SELECT p FROM PlayerModel p JOIN p.football_categories c WHERE c = :category")
  List<PlayerModel> findByCategories(FootballCategoryModel category);
}
