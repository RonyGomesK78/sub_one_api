package com.example.sub_one_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.services.FootballCategoryService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/football_categories")
public class FootballCategoryController {
  @Autowired
  public FootballCategoryService footballCategoryService;

  @GetMapping
  public ResponseEntity<List<FootballCategoryModel>> getCategories() {
    var footballCategories = footballCategoryService.getAllFootballCategories();
    return ResponseEntity.status(HttpStatus.OK).body(footballCategories);
  }
}
