package com.example.sub_one_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sub_one_api.models.FootballPositionModel;
import com.example.sub_one_api.repositories.FootballPositionRepository;

@Service
public class FootballPositionService {
  @Autowired
  private FootballPositionRepository footballPositionRepository;

  public List<FootballPositionModel> getAllFootballPositions() {
    return footballPositionRepository.findAll();
  }

  public Optional<FootballPositionModel> getFootballPositionById(String positionId) {
    return footballPositionRepository.findById(positionId);
  }
}
