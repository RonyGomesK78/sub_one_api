package com.example.sub_one_api.services;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sub_one_api.services.jwt.JwtService;
import com.example.sub_one_api.models.UserModel;
import com.example.sub_one_api.repositories.UserRepository;
import com.example.sub_one_api.enums.Role;
import com.example.sub_one_api.dtos.auth.AuthenticationRequest;
import com.example.sub_one_api.dtos.auth.AuthenticationResponse;
import com.example.sub_one_api.dtos.auth.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JwtService jwtService;

  public AuthenticationResponse register(RegisterRequest request) {

    Role userRole = Role.valueOf(request.role());

    var user = UserModel.builder()
        .firstname(request.firstname())
        .lastname(request.lastname())
        .email(request.email())
        .password(passwordEncoder.encode(request.password()))
        .role(userRole)
        .build();

    userRepository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
