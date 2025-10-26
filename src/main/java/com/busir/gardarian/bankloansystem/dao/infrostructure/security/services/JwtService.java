package com.busir.gardarian.bankloansystem.dao.infrostructure.security.services;

import com.busir.gardarian.bankloansystem.dao.infrostructure.security.dto.JwtAuthenticationDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.access}")
    private Long expirationAccess;

    @Value("${jwt.expiration.refresh}")
    private Long expirationRefresh;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public JwtAuthenticationDto generateAuthToken(UserDetails userDetails) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setAccessToken(generateAccessToken(userDetails));
        jwtDto.setRefreshToken(generateRefreshToken(userDetails));

        return jwtDto;
    }

    public JwtAuthenticationDto refreshAuthToken(UserDetails userDetails, String refreshToken) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setAccessToken(generateAccessToken(userDetails));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    private String generateRefreshToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationRefresh))
                .signWith(getSigningKey())
                .compact();

    }

    private String generateAccessToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationAccess))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}