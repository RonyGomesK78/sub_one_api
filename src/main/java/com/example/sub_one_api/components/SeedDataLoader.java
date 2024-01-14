package com.example.sub_one_api.components;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.sub_one_api.models.FootballPositionModel;
import com.example.sub_one_api.repositories.FootballPositionRepository;

@Component
public class SeedDataLoader implements CommandLineRunner {

  private final FootballPositionRepository footballPositionRepository;

  public SeedDataLoader(FootballPositionRepository footballPositionRepository) {
      this.footballPositionRepository = footballPositionRepository;
  }

    @Override
    @Transactional
    public void run(String... args) {
      // Check if data already exists
      if (footballPositionRepository.count() == 0) {
        // Insert initial data
        Set<FootballPositionModel> positionsModel = new HashSet<>();
       
        positionsModel.add(new FootballPositionModel("GR", "Guarda Redes"));
        positionsModel.add(new FootballPositionModel("DC", "Defesa Central"));
        positionsModel.add(new FootballPositionModel("LE", "Lateral Esquerdo"));
        positionsModel.add(new FootballPositionModel("LD", "Lateral Direito"));
        positionsModel.add(new FootballPositionModel("MD", "Médio Defensivo"));
        positionsModel.add(new FootballPositionModel("MC", "Médio Centro"));
        positionsModel.add(new FootballPositionModel("MC", "Médio Centro"));
        positionsModel.add(new FootballPositionModel("EE", "Extremo Esquerdo"));
        positionsModel.add(new FootballPositionModel("EE", "Extremo Esquerdo"));
        positionsModel.add(new FootballPositionModel("A", "Avançado"));

        footballPositionRepository.saveAll(positionsModel);
      }

    }
}
