package com.kh.vira_dev.ecommerce.io.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private String locale;
    private String slug;
    private List<String> role;
    private String bio;
    private String company;
    private Instant lastLogin;
    private Instant emailValidated;
    private Instant phoneValidated;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @JsonProperty("credentials")
    private List<CredentialResponse> credentials;
    @JsonProperty("socialProfiles")
    private List<SocialProfileResponse> socialProfiles;
    private String token;
}
