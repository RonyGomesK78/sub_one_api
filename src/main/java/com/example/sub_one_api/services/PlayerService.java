package com.example.sub_one_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.models.PlayerModel;
import com.example.sub_one_api.repositories.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {

  @Autowired
	private PlayerRepository playerRepository;

  @Transactional
  public PlayerModel save(PlayerModel playerModel) {
    return playerRepository.save(playerModel);
  }

  public List<PlayerModel> getAllPlayers() {
    return playerRepository.findAll();
  }

  public List<PlayerModel> getAllPlayersByCategory(FootballCategoryModel category) {
    return playerRepository.findByCategories(category);
  }
}
