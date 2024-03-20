package com.pritam.controller;

import com.pritam.response.UserCartResponse;
import com.pritam.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{productId}/{userId}")
    public ResponseEntity<Void> addToCart(@PathVariable Long productId, @PathVariable Long userId) {
        cartService.addToCart(productId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{productId}/{userId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId, @PathVariable Long userId) {
        cartService.removeFromCart(productId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserCartResponse>> getCartByUser(@PathVariable Long userId) {
        List<UserCartResponse> cart = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
