package com.kh.vira_dev.ecommerce.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JwtService {

    SecretKey getSigningKey();

    String generateToken(String subject);

    String generateToken(Map<String , Object> claims , String subject);

    String extractEmail(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token , UserDetails userDetails);

}
