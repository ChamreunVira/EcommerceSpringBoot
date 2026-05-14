package com.kh.vira_dev.ecommerceapi.security;

import com.kh.vira_dev.ecommerceapi.dto.request.AuthRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.UserResponse;
import com.kh.vira_dev.ecommerceapi.entity.User;

public interface AuthService {

    UserResponse singIn(AuthRequest request);

    User authenticated();

}
