package com.pritam.service;

import com.pritam.entity.ProductEntity;
import com.pritam.request.AddProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProductById(Long id);
    ProductEntity addProduct(AddProductRequest request);
    ProductEntity updateProduct(Long id, AddProductRequest product);
    void deleteProduct(Long id);
}
