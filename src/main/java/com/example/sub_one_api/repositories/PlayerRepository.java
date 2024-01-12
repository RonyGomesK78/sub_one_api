package com.example.sub_one_api.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.sub_one_api.models.Player;;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface PlayerRepository extends CrudRepository<Player, UUID> {

}
