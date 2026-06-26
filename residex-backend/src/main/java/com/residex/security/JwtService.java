package com.residex.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(
            String email,
            String role
    ) {

        Date now = new Date();

        Date expiryDate =
                new Date(
                        now.getTime()
                                + jwtProperties.getExpiration()
                );

        Key signingKey =
                Keys.hmacShaKeyFor(
                        jwtProperties.getSecret()
                                .getBytes(StandardCharsets.UTF_8)
                );

        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(
            String token,
            String email
    ) {

        return extractUsername(token)
                .equals(email)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token
    ) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(
            String token
    ) {

        Key signingKey =
                Keys.hmacShaKeyFor(
                        jwtProperties.getSecret()
                                .getBytes(StandardCharsets.UTF_8)
                );

        return Jwts.parser()
                .verifyWith((SecretKey) signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}