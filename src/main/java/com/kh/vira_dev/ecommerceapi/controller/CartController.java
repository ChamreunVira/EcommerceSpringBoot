package com.kh.vira_dev.ecommerceapi.controller;

import com.kh.vira_dev.ecommerceapi.dto.request.AddItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.CartResponse;
import com.kh.vira_dev.ecommerceapi.payload.ApiResponse;
import com.kh.vira_dev.ecommerceapi.security.UserPrincipal;
import com.kh.vira_dev.ecommerceapi.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ResponseEntity<ApiResponse<CartResponse>> addItemToCard(@AuthenticationPrincipal UserPrincipal userPrincipal,@Valid @RequestBody AddItemRequest request) {
        var response = cartService.addItem(userPrincipal.getId() , request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
