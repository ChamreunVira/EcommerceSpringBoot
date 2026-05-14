package com.kh.vira_dev.ecommerceapi.service.impl;

import com.kh.vira_dev.ecommerceapi.dto.request.AddItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.request.UpdateItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.CartResponse;
import com.kh.vira_dev.ecommerceapi.entity.Cart;
import com.kh.vira_dev.ecommerceapi.entity.Product;
import com.kh.vira_dev.ecommerceapi.entity.User;
import com.kh.vira_dev.ecommerceapi.exception.ResourceNotFoundException;
import com.kh.vira_dev.ecommerceapi.repository.CartItemRepository;
import com.kh.vira_dev.ecommerceapi.repository.CartRepository;
import com.kh.vira_dev.ecommerceapi.repository.ProductRepository;
import com.kh.vira_dev.ecommerceapi.repository.UserRepository;
import com.kh.vira_dev.ecommerceapi.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse addItem(int userId, AddItemRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart"));
        Cart cart = new Cart();
        cart.setUser(user);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
//        cart.setTotalAmount(product.getPrice() * request.getQuantity());
//        cart.setCartItems();
        return null;
    }

    @Override
    public CartResponse getCart() {
        return null;
    }

    @Override
    public CartResponse updateItem(int userId, short itemId, UpdateItemRequest request) {
        return null;
    }

    @Override
    public void removeItem(int userId, short itemId) {

    }

    @Override
    public void clearCart(int userId) {

    }
}
