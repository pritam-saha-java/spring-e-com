package com.masai.testing;

import com.pritam.entity.CartEntity;
import com.pritam.entity.ProductEntity;
import com.pritam.repository.CartRepository;
import com.pritam.repository.ProductRepository;
import com.pritam.response.UserCartResponse;
import com.pritam.service.implementation.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToCart() {
        // Mocking
        CartEntity cart = new CartEntity();
        cart.setProductId(1L);
        cart.setUserId(1L);

        when(cartRepository.save(any())).thenReturn(cart);

        // Testing
        cartService.addToCart(1L, 1L);

        // Verification
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void testRemoveFromCart() {
        // Mocking
        CartEntity cart = new CartEntity();
        cart.setProductId(1L);
        cart.setUserId(1L);

        when(cartRepository.findByProductIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(cart));

        // Testing
        cartService.removeFromCart(1L, 1L);

        // Verification
        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    public void testGetCartByUser() {
        // Mocking
        CartEntity cart1 = new CartEntity();
        cart1.setProductId(1L);
        cart1.setUserId(1L);

        CartEntity cart2 = new CartEntity();
        cart2.setProductId(2L);
        cart2.setUserId(1L);

        List<CartEntity> cartEntities = new ArrayList<>();
        cartEntities.add(cart1);
        cartEntities.add(cart2);

        when(cartRepository.findAllByUserId(anyLong())).thenReturn(cartEntities);

        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        product1.setProductName("Product 1");
        product1.setPrice(10.0);

        ProductEntity product2 = new ProductEntity();
        product2.setId(2L);
        product2.setProductName("Product 2");
        product2.setPrice(20.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        // Testing
        List<UserCartResponse> cartResponses = cartService.getCartByUser(1L);

        // Verification
        assertEquals(2, cartResponses.size());
        assertEquals("Product 1", cartResponses.get(0).getProductName());
        assertEquals(10.0, cartResponses.get(0).getPrice());
        assertEquals("Product 2", cartResponses.get(1).getProductName());
        assertEquals(20.0, cartResponses.get(1).getPrice());
    }
}

