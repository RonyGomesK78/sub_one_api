package com.example.sub_one_api.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.sub_one_api.dtos.GuardianRecordDto;
import com.example.sub_one_api.dtos.PlayerRecordDto;
import com.example.sub_one_api.models.GuardianModel;
import com.example.sub_one_api.models.PlayerModel;
import com.example.sub_one_api.services.PlayerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@PostMapping
	public ResponseEntity<PlayerModel> createPlayer(@RequestBody @Valid() PlayerRecordDto playerRecordDto) {

		var playerModel = new PlayerModel();
		Set<GuardianModel> guardiansModel = new HashSet<>(); 
		
		LocalDate birthDateFormatted = LocalDate.parse(playerRecordDto.birthdate(), DateTimeFormatter.ISO_DATE);
	
		BeanUtils.copyProperties(playerRecordDto, playerModel);

		playerModel.setBirthdate(birthDateFormatted);
		playerModel.setCountryCode(playerRecordDto.country_code());
		playerModel.setPhoneNumber(playerRecordDto.phone_number());
	
		for (GuardianRecordDto guardian : playerRecordDto.guardians()) {
			guardiansModel.add(new GuardianModel(guardian.name(), guardian.phone_number(), guardian.country_code(), guardian.email()));
		}

		playerModel.setGuardians(guardiansModel);

		var playerCreated = playerService.save(playerModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated);
	}

	@GetMapping
	public ResponseEntity<List<PlayerModel>> getPlayers() {
		var players = playerService.getAllPlayers();
		return ResponseEntity.status(HttpStatus.OK).body(players);
	}
	
}