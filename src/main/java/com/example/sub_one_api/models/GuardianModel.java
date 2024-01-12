package com.example.sub_one_api.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "Guardians")
public class GuardianModel implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;

  private Long phoneNumber;

  private String countryCode;

  @Email
  private String email;

  @ManyToMany(mappedBy = "guardians")
  private Set<PlayerModel> wards;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public GuardianModel(String name, Long phoneNumber, String countryCode, String email ) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.countryCode = countryCode;
    this.email = email;
  }  
  public UUID getId() {
    return id;
  }  
  public String getName() {
    return name;
  }  
  public String getCountryCode() {
    return countryCode;
  }  
  public Long getPhoneNumber() {
    return phoneNumber;
  }  
  public String getEmail() {
    return email;
  }
}
