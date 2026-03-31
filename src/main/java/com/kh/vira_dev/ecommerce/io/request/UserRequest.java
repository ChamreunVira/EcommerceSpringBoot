package com.kh.vira_dev.ecommerce.io.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kh.vira_dev.ecommerce.entity.Credential;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 2 , max = 50 , message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Size(min = 5 , max = 50 , message = "Email must be between 5 and 50 characters.")
    @Email(message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    private String phone;

    @NotNull(message = "Avatar is required.")
    private MultipartFile avatar;

    @NotBlank(message = "Local is required.")
    private String locale;

    private String role;

    @NotBlank(message = "Bio is required.")
    @Size(min = 3 , max = 100 , message = "Bio must be at least more than 3 characters.")
    private String bio;

    @NotBlank(message = "Company is required.")
    private String company;

    @NotBlank(message = "Password is required.")
    @Size(min = 5 , max = 100 , message = "Password must be at least 5 characters.")
    private String password;

    private String platform;

    private String platformUser;

}
