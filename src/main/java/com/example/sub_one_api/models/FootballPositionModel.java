package com.example.sub_one_api.models;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Position")
public class FootballPositionModel implements Serializable {
  
  @Id
  private String id;
  
  private String name;

  @ManyToMany(mappedBy = "football_positions")
  private Set<PlayerModel> players;

  public FootballPositionModel() {}

  public FootballPositionModel(String id) {
    this.id = id;
  }

  public FootballPositionModel(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
