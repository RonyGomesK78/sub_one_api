package com.example.sub_one_api.components;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.models.FootballPositionModel;
import com.example.sub_one_api.repositories.FootballCategoryRepository;
import com.example.sub_one_api.repositories.FootballPositionRepository;

@Component
public class SeedDataLoader implements CommandLineRunner {

  private final FootballPositionRepository footballPositionRepository;
  private final FootballCategoryRepository footballCategoryRepository;
  
  public SeedDataLoader(FootballPositionRepository footballPositionRepository, FootballCategoryRepository footballCategoryRepository) {
      this.footballPositionRepository = footballPositionRepository;
      this.footballCategoryRepository = footballCategoryRepository;
  }

  @Override
  @Transactional
  public void run(String... args) {
    // Check if data already exists
    if (footballPositionRepository.count() == 0) {
      
      footballPositionRepository.save(new FootballPositionModel("GR", "Guarda Redes"));
      footballPositionRepository.save(new FootballPositionModel("DC", "Defesa Central"));
      footballPositionRepository.save(new FootballPositionModel("LD", "Lateral Direito"));
      footballPositionRepository.save(new FootballPositionModel("LE", "Lateral Esquerdo"));
      footballPositionRepository.save(new FootballPositionModel("MD", "Médio Defensivo"));
      footballPositionRepository.save(new FootballPositionModel("MC", "Médio Centro"));
      footballPositionRepository.save(new FootballPositionModel("MO", "Médio Ofensivo"));
      footballPositionRepository.save(new FootballPositionModel("EE", "Extremo Esquerdo"));
      footballPositionRepository.save(new FootballPositionModel("ED", "Extremo Direito"));
      footballPositionRepository.save(new FootballPositionModel("PL", "Ponta Lança"));
    }
    
    if (footballCategoryRepository.count() == 0) {

      footballCategoryRepository.save(new FootballCategoryModel("sub-6", "SUB-6"));
      footballCategoryRepository.save(new FootballCategoryModel("sub-11", "SUB-11"));
      footballCategoryRepository.save(new FootballCategoryModel("sub-13", "SUB-13"));
      footballCategoryRepository.save(new FootballCategoryModel("sub-15", "SUB-15"));
      footballCategoryRepository.save(new FootballCategoryModel("senior-feminino", "Sénior Feminino"));
    }
  }
}
