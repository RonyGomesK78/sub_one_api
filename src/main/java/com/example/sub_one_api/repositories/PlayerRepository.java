package com.example.sub_one_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sub_one_api.models.PlayerModel;;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {}
