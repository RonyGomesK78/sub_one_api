package com.example.sub_one_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.example.sub_one_api.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByEmail(String email);
}
