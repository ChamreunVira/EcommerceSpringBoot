package com.kh.vira_dev.ecommerceapi.service.impl;

import com.kh.vira_dev.ecommerceapi.dto.request.AddItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.request.UpdateItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.CartItemResponse;
import com.kh.vira_dev.ecommerceapi.dto.response.CartResponse;
import com.kh.vira_dev.ecommerceapi.entity.Cart;
import com.kh.vira_dev.ecommerceapi.entity.CartItem;
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

import java.math.BigDecimal;
import java.time.Instant;

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

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));

        Cart cart = new Cart();
        cart.setUser(user);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        cartItem.setCart(cart);
        cartItem.setUnitPrice(BigDecimal.valueOf(product.getPrice()));
        cart.getCartItems().add(cartItem);

        Cart saved = cartRepository.save(cart);
        return toResponse(saved);
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

    private CartResponse toResponse(Cart cart) {

        return CartResponse
                .builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .cartItems(cart.getCartItems()
                        .stream()
                        .map(this::toCartItemResponse)
                        .toList())
                .totalItems(cart.getCartItems().size())
                .totalAmount(0.0)
                .updatedAt(Instant.now())
                .build();
    }

    private CartItemResponse toCartItemResponse(CartItem cartItem) {

        Product product = cartItem.getProduct();
        BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());

        BigDecimal discountPrice = productPrice.multiply(BigDecimal.valueOf(product.getDiscount()));
        BigDecimal finalPrice = productPrice.subtract(discountPrice);

        return CartItemResponse
                .builder()
                .id(cartItem.getId())
                .productId(product.getId())
                .productName(product.getName())
                .productImage(product.getImage().getFirst())
                .unitPrice(productPrice)
                .discountRat(product.getDiscount())
                .finalPrice(finalPrice)
                .quantity(cartItem.getQuantity())
                .subtotal(finalPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .build();
    }
}
