package com.example.sub_one_api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Players")
public class PlayerModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    private String nickname;

    private LocalDate birthdate;

    @NotBlank(message = "Genre is required")
    private String genre;

    private String countryCode;

    private Long phoneNumber;

    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "guardianship", 
      joinColumns = @JoinColumn(name = "player_id"), 
      inverseJoinColumns = @JoinColumn(name = "guardian_id"))
    private Set<GuardianModel> guardians;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "players_positions",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<FootballPositionModel> football_positions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "players_categories",
      joinColumns = @JoinColumn(name = "player_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<FootballCategoryModel> football_categories;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public LocalDate getBirthdate() {
      return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
      this.birthdate = birthdate;
    }

    public String getGenre() {
      return genre;
    }

    public void setGenre(String genre) {
      this.genre = genre;
    }

    public String getCountryCode() {
      return countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public Long getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public LocalDateTime getCreatedAt() {
      return createdAt;
    }

    public Set<GuardianModel> getGuardians() {
      return guardians;
    }

    public void setGuardians(Set<GuardianModel> guards) {
      this.guardians = guards;
    }
}
