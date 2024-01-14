package com.example.sub_one_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sub_one_api.models.FootballPositionModel;
import com.example.sub_one_api.services.FootballPositionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/football_positions")
public class FootballPositionController {
  
  @Autowired
  private FootballPositionService footballPositionService;

  @GetMapping
  public ResponseEntity<List<FootballPositionModel>> getPositions() {
    var football_positions = footballPositionService.getAllFootballPositions();
    return ResponseEntity.status(HttpStatus.OK).body(football_positions);
  }
}
