package com.example.sub_one_api.services.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Date;

import com.example.sub_one_api.models.UserModel;

@Service
public class JwtService {

  private static final String SECRET_KEY = "0+aGmuqVRHAxbaLJTRB9y/SZUY97JPmb6HV8ts+/ZV5pz+EcxjnsXMaHtkGHlKYE";
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T>claimsResolver) {

    final Claims claims = extractAllClaims(token);

    return claimsResolver.apply(claims);
  }

  public String generateToken(UserModel userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaim, UserModel user) {
    long currentTimeMillis = System.currentTimeMillis();
    long expirationTimeMillis = currentTimeMillis + 1000L * 60 * 60 * 24 * 365; // 1 year

    extraClaim.put("roles", user.getAuthorities());
    extraClaim.put("firstname", user.getFirstname());
    extraClaim.put("lastname", user.getLastname());

    return Jwts
        .builder()
        .setClaims(extraClaim)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(currentTimeMillis))
        .setExpiration(new Date(expirationTimeMillis))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();

  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {

    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

    return Keys.hmacShaKeyFor(keyBytes);
  }
}
