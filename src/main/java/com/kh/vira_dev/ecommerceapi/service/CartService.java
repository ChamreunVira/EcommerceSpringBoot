package com.kh.vira_dev.ecommerceapi.service;

import com.kh.vira_dev.ecommerceapi.dto.request.AddItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.request.UpdateItemRequest;
import com.kh.vira_dev.ecommerceapi.dto.response.CartResponse; 

public interface CartService {

//    cart service implement step by step
//    1. create request and response
//    - AddItemRequest(productId , quantity)
//    - CartResponse(cartId, items[] , totalItems , totalAmount);
//    items[] = itemId, productId, productName, price, discount, quantity, subtotal

//    handle from controller:
//    - POST:: /cart/items add tp card
//    - GET:: /cart return cart response back

    CartResponse addItem(int userId, AddItemRequest request);

    CartResponse getCart();

    CartResponse updateItem(int userId, short itemId, UpdateItemRequest request);

    void removeItem(int userId, short itemId);

    void clearCart(int userId);


}
