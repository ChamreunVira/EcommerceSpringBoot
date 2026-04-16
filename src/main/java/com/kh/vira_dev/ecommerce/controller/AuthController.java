package com.kh.vira_dev.ecommerce.controller;

import com.kh.vira_dev.ecommerce.io.request.AuthRequest;
import com.kh.vira_dev.ecommerce.io.request.UserRequest;
import com.kh.vira_dev.ecommerce.io.response.UserResponse;
import com.kh.vira_dev.ecommerce.payload.ApiResponse;
import com.kh.vira_dev.ecommerce.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @ModelAttribute UserRequest request) {
        var response = authService.register(request);
        ResponseCookie cookie = ResponseCookie.from("token" , response.getToken())
                .maxAge(Duration.ofDays(7))
                .secure(false)
                .httpOnly(true)
                .sameSite("Strict")
                .path("/")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success("Created user successfully", response));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody AuthRequest request) {
        var response = authService.login(request);
        ResponseCookie cookie = ResponseCookie.from("token" , response.getToken())
                .maxAge(Duration.ofDays(7))
                .secure(false)
                .httpOnly(true)
                .sameSite("Strict")
                .path("/")
                .build();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE , cookie.toString())
                .body(ApiResponse.success("Login successfully", response));
    }

}
