package com.example.sub_one_api.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sub_one_api.dtos.GuardianRecordDto;
import com.example.sub_one_api.dtos.PlayerRecordDto;
import com.example.sub_one_api.models.FootballCategoryModel;
import com.example.sub_one_api.models.FootballPositionModel;
import com.example.sub_one_api.models.GuardianModel;
import com.example.sub_one_api.models.PlayerModel;
import com.example.sub_one_api.services.FootballCategoryService;
import com.example.sub_one_api.services.FootballPositionService;
import com.example.sub_one_api.services.PlayerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private FootballPositionService positionService;
	
	@Autowired
	private FootballCategoryService categoryService;

	@PostMapping
	public ResponseEntity<PlayerModel> createPlayer(@RequestBody @Valid() PlayerRecordDto playerRecordDto) {
		var playerModel = new PlayerModel();

		Set<GuardianModel> guardiansModel = new HashSet<>(); 
		Set<FootballPositionModel> positionModel = new HashSet<>();
		Set<FootballCategoryModel> categoryModel = new HashSet<>();

		Optional<FootballPositionModel> existingPosition = null;
		Optional<FootballCategoryModel> existingCategory = null;

		if (playerRecordDto.position() != null && playerRecordDto.position().trim() != "") {
			
			existingPosition = positionService.getFootballPositionById(playerRecordDto.position());

			if (existingPosition.isPresent()) {
				positionModel.add(existingPosition.get());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			playerModel.setPositons(positionModel);
		}
		
		if (playerRecordDto.category() != null && playerRecordDto.category().trim() != "") {

			existingCategory = categoryService.getFootballCategoryById(playerRecordDto.category());

			if (existingCategory.isPresent()) {
				categoryModel.add(existingCategory.get());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			playerModel.setCategories(categoryModel);
		}

		LocalDate birthDateFormatted = LocalDate.parse(playerRecordDto.birthdate(), DateTimeFormatter.ISO_DATE);
	
		BeanUtils.copyProperties(playerRecordDto, playerModel);

		playerModel.setBirthdate(birthDateFormatted);
		playerModel.setCountryCode(playerRecordDto.country_code());
		playerModel.setPhoneNumber(playerRecordDto.phone_number());

		if (playerRecordDto.guardians() != null) {
			for (GuardianRecordDto guardian : playerRecordDto.guardians()) {
				guardiansModel.add(new GuardianModel(guardian.name(), guardian.phone_number(), guardian.country_code(), guardian.email()));
			}
	
			playerModel.setGuardians(guardiansModel);
		}

		var playerCreated = playerService.save(playerModel);

		return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated);
	}

	@GetMapping
	public ResponseEntity<List<PlayerModel>> getPlayers(@RequestParam(required = false) String category) {
		if (category != null && !category.isEmpty()) {
			var categoryModelOptional = categoryService.getFootballCategoryById(category);
			if (categoryModelOptional.isPresent()) {
					var categoryModel = categoryModelOptional.get();
					var players = playerService.getAllPlayersByCategory(categoryModel);
					return ResponseEntity.status(HttpStatus.OK).body(players);
			} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} else {
			var players = playerService.getAllPlayers();
			return ResponseEntity.status(HttpStatus.OK).body(players);
	}
		
	}
	
}