package com.kh.vira_dev.ecommerce.io.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialResponse {
    private Long id;
    private String providerId;
    private String providerKey;
    private String hasher;
    private String passwordHash;
    private String passwordSalt;
}
