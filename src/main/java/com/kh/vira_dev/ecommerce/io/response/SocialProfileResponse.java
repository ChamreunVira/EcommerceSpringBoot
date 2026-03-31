package com.kh.vira_dev.ecommerce.io.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialProfileResponse {
    private Long id;
    private String socialPlatform;
    private String platformUser;
    private LocalDate createdAt;
}
