package com.kh.vira_dev.ecommerce.mapper;

import com.kh.vira_dev.ecommerce.entity.User;
import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;

public interface AuthMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User request);

    void updateFromRequest(User user, AuthRequest request);

}
