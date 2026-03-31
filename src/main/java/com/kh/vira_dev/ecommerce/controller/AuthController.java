package com.kh.vira_dev.ecommerce.controller;

import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import com.kh.vira_dev.ecommerce.payload.ApiResponse;
import com.kh.vira_dev.ecommerce.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @ModelAttribute UserRequest request) {
        var response = authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("Created user successfully", response));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody AuthRequest request) {
        var response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successfully", response));
    }

}
