package com.kh.vira_dev.ecommerce.jwt.impl;

import com.kh.vira_dev.ecommerce.jwt.JwtConfig;
import com.kh.vira_dev.ecommerce.jwt.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtServiceImpl extends JwtConfig implements JwtService {

    @Override
    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(getSecretBase64().getBytes());
    }

    @Override
    public String generateToken(String subject) {
        return generateToken(new HashMap<>() , subject);
    }

    @Override
    public String generateToken(Map<String, Object> claims, String subject) {
        return builderToken(claims , subject , getExpiration());
    }

    @Override
    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public boolean isTokenValid(String token , UserDetails userDetails) {
        final String userEmail = extractEmail(token);
        return (userDetails.getUsername().equals(userEmail) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token , Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (MalformedJwtException ex) {
            log.info("Invalid format jwt token.");
            throw new JwtException("Invalid format jwt token.");
        } catch (UnsupportedJwtException ex) {
            log.info("Invalid jwt token is not support.");
            throw new JwtException("Invalid Jwt token");
        }catch (ExpiredJwtException ex) {
            log.info("Jwt token is expired.");
            throw new JwtException("Jwt token is expired.");
        }catch (JwtException ex) {
            log.info("Jwt token not found.");
            throw new JwtException("Jwt token not found.");
        }
    }

    private String builderToken(Map<String, Object> claims , String subject , Long expiration) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
}
