package com.pritam.service.implementation;

import com.pritam.entity.CartEntity;
import com.pritam.exception.BadRequestException;
import com.pritam.repository.CartRepository;
import com.pritam.repository.ProductRepository;
import com.pritam.response.UserCartResponse;
import com.pritam.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addToCart(Long productId, Long userId) {
        CartEntity cart = new CartEntity();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long productId, Long userId) {
        CartEntity cart = cartRepository.findByProductIdAndUserId(productId, userId)
                .orElseThrow(() -> new BadRequestException("Product not found in the user's cart"));
        cartRepository.delete(cart);
    }

    @Override
    public List<UserCartResponse> getCartByUser(Long userId) {
        List<CartEntity> entities = cartRepository.findAllByUserId(userId);
        List<UserCartResponse> responses = new ArrayList<>();
        entities.forEach(cartEntity -> {
            productRepository.findById(cartEntity.getProductId()).ifPresent(productEntity -> {
                UserCartResponse cartResponse = new UserCartResponse();
                cartResponse.setPrice(productEntity.getPrice());
                cartResponse.setProductName(productEntity.getProductName());
                responses.add(cartResponse);
            });
        });
        return responses;
    }

}
