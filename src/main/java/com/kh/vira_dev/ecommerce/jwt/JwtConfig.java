package com.kh.vira_dev.ecommerce.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class JwtConfig {

    @Value("${jwt.secretBase64}")
    private String secretBase64;

    @Value("${jwt.expiration}")
    private Long expiration;

}
