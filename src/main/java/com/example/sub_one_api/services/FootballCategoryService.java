package com.example.sub_one_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.repositories.FootballCategoryRepository;

@Service
public class FootballCategoryService {
  @Autowired
  private FootballCategoryRepository footballCategoryRepository;

  public List<FootballCategoryModel> getAllFootballCategories() {
    return footballCategoryRepository.findAll();
  }

  public Optional<FootballCategoryModel> getFootballCategoryById(String categoryId) {
    return footballCategoryRepository.findById(categoryId);
  }
}
