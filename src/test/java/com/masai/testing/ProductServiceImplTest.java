package com.masai.testing;

import com.pritam.entity.ProductEntity;
import com.pritam.repository.ProductRepository;
import com.pritam.request.AddProductRequest;
import com.pritam.service.ProductService;
import com.pritam.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        // Given
        List<ProductEntity> productList = new ArrayList<>();
        ProductEntity entity = new ProductEntity();
        entity.setProductName("Laptop");
        entity.setId(1L);
        entity.setPrice(50000.00);
        productList.add(entity);
        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<ProductEntity> result = productService.getAllProducts();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        // Given
        ProductEntity entity = new ProductEntity();
        entity.setProductName("Mobile");
        entity.setId(2L);
        entity.setPrice(20000.00);
        when(productRepository.findById(1L)).thenReturn(Optional.of(entity));

        // When
        Optional<ProductEntity> result = productService.getProductById(1L);

        // Then
        assertEquals(entity, result.get());
    }

    @Test
    public void testAddProduct() {
        // Given
        AddProductRequest request = new AddProductRequest();
        request.setProductName("Phone");
        request.setPrice(1000.00);
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        ProductEntity result = productService.addProduct(request);

        // Then
        assertEquals(request.getProductName(), result.getProductName());
        assertEquals(request.getPrice(), result.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        // When
        productService.deleteProduct(1L);

        // Then
        verify(productRepository, times(1)).deleteById(1L);
    }
}
