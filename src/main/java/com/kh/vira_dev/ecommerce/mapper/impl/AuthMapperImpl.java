package com.kh.vira_dev.ecommerce.mapper.impl;

import com.kh.vira_dev.ecommerce.constant.Locale;
import com.kh.vira_dev.ecommerce.constant.Platform;
import com.kh.vira_dev.ecommerce.constant.Role;
import com.kh.vira_dev.ecommerce.entity.Credential;
import com.kh.vira_dev.ecommerce.entity.SocialProfile;
import com.kh.vira_dev.ecommerce.entity.User;
import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import com.kh.vira_dev.ecommerce.mapper.AuthMapper;
import com.kh.vira_dev.ecommerce.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthMapperImpl implements AuthMapper {

    private final FileUploadService fileUploadService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(UserRequest request) {
        if(request == null) return null;
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setSlug(request.getName().trim().toLowerCase().replaceAll(" ", "-"));
        user.setPhone(request.getPhone());
        user.setBio(request.getBio());
        user.setCompany(request.getCompany());
        if (request.getRole() != null) {
            user.setRole(List.of(Role.valueOf(request.getRole())));
        } else {
            user.setRole(List.of(Role.STAFF));
        }
        if(request.getAvatar() != null && !request.getAvatar().isEmpty()) {
            String fileName = fileUploadService.upload(request.getAvatar());
            user.setAvatar(fileName);
        }

        user.setLocale(Locale.valueOf(request.getLocale()));
        user.setLastLogin(Instant.now());
        user.setEmailValidated(null);
        user.setPhoneValidated(null);
        Credential credential = toCredentials(user , request);
        user.setCredentials(List.of(credential));
        user.setSocialProfiles(List.of(toSocialProfile(user , request)));
        return user;
    }

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .bio(user.getBio())
                .company(user.getCompany())
                .role(user.getRole().stream().map(Enum::name).toList())
                .avatar(user.getAvatar())
                .locale(user.getLocale().name())
                .lastLogin(user.getLastLogin())
                .emailValidated(user.getEmailValidated())
                .phoneValidated(user.getPhoneValidated())
                .build();
    }

    @Override
    public void updateFromRequest(User user, AuthRequest request) {

    }

    private Credential toCredentials(User user , UserRequest request) {
        Credential credential = new Credential();
        credential.setHasher(null);
        credential.setUser(user);
        credential.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        credential.setPasswordSalt(null);
        credential.setHasher(null);
        return credential;
    }

    private SocialProfile toSocialProfile(User user , UserRequest request) {
        SocialProfile socialProfile = new SocialProfile();
        socialProfile.setPlatform(Platform.valueOf(request.getPlatform()));
        socialProfile.setPlatformName(request.getPlatform());
        return socialProfile;
    }

}
