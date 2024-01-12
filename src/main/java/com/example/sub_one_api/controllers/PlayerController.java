package com.example.sub_one_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.sub_one_api.models.Player;
import com.example.sub_one_api.records.PlayerRecord;
import com.example.sub_one_api.repositories.PlayerRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/api/players")
	public @ResponseBody Player createPlayer(@RequestBody PlayerRecord body) {
		
		Player player = new Player();
		player.setName(body.name());

		playerRepository.save(player);
		return player;
	}
}