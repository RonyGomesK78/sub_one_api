package com.example.sub_one_api;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

	private static final String playerNameOne = "Romilton Alves Gomes";
	private static final String playerOneAge = "31";
	private static final String playerOneGenre = "M";
	private static final String playerOnePosition = "MC";

	private static final String playerNameTwo = "Kiro Gabriel Pires Gomes";
		private static final String playerTwoAge = "5";
	private static final String playerTwoGenre = "M";
	private static final String playerTwoPosition = "AT";

	private final AtomicLong counter = new AtomicLong();

	@CrossOrigin(origins = "*")
	@GetMapping("/api/players")
	public Player[] getPlayers() {
		
		Guardian [] playerOneGuardians = new Guardian[2];
		playerOneGuardians[0] = new Guardian("Perpétua Antónia Alves", "9929618");
		playerOneGuardians[1] = new Guardian("Albertino Alberto Gomes", "2343532345");

		Guardian [] playerTwoGuardians = new Guardian[2];
		playerTwoGuardians[0] = new Guardian("Romilton Alves Gomes", "9369726");
		playerTwoGuardians[1] = new Guardian("Loredana Delgado Pires Gomes", "9988249");

		Player[] players = new Player[2];
		players[0] = new Player(counter.incrementAndGet(), playerNameOne, playerOneAge, playerOneGenre, "", playerOnePosition, playerOneGuardians);
		players[1] = new Player(counter.incrementAndGet(), playerNameTwo,  playerTwoAge, playerTwoGenre, "", playerTwoPosition, playerTwoGuardians );

		return players;
	}	
}