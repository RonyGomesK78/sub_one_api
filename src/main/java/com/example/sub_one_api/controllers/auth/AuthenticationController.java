package com.example.sub_one_api.controllers.auth;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sub_one_api.dtos.auth.*;

import com.example.sub_one_api.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {

    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
}
