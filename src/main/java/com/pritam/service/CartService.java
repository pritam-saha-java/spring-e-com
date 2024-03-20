package com.pritam.service;

import com.pritam.entity.CartEntity;
import com.pritam.response.UserCartResponse;

import java.util.List;

public interface CartService {
    void addToCart(Long productId, Long userId);
    void removeFromCart(Long productId, Long userId);
    List<UserCartResponse> getCartByUser(Long userId);
}
