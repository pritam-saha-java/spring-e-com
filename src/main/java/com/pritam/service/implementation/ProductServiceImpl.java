package com.pritam.service.implementation;

import com.pritam.entity.ProductEntity;
import com.pritam.exception.BadRequestException;
import com.pritam.repository.ProductRepository;
import com.pritam.request.AddProductRequest;
import com.pritam.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> response = new ArrayList<>();
        for (ProductEntity productEntity : productRepository.findAll()) {
            response.add(productEntity);
        }
        return response;
    }

    @Override
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductEntity addProduct(AddProductRequest request) {
        ProductEntity product = new ProductEntity();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, AddProductRequest productRequest) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductEntity product = optionalProduct.get();
            product.setProductName(productRequest.getProductName());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        } else {
            throw new BadRequestException("Please enter a valid product id");
        }
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
