package com.kh.vira_dev.ecommerce.security;

import com.kh.vira_dev.ecommerce.entity.User;
import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthService {

    UserResponse register(UserRequest request);

    UserResponse login(AuthRequest request);

    UserResponse create(UserRequest request);

    UserResponse update(UserRequest request);

    UserResponse me();

    Page<UserResponse> getAll(Pageable pageable);

    UserResponse getById(Long id);

    void delete(Long id);

    User getAuthenticatedUser();
}
