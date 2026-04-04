package org.example.sr_group_03_spring_mini_project.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    @Value("${jwt.secret_key}")
    private String SECRET_KEY;
    private final Instant ISSUED_AT = Instant.now();


    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(userDetails.getUsername())
                .issuer("group-3.io")
                .signWith(secretKey())
                .issuedAt(Date.from(ISSUED_AT))
                .expiration(Date.from(ISSUED_AT.plus(10, ChronoUnit.HOURS)))
                .compact();
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }


    public Date extractExpDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExp(String token) {
        return extractExpDate(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String email = extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExp(token);
    }
}
