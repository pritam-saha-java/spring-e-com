package com.pritam.controller;

import com.pritam.entity.ProductEntity;
import com.pritam.entity.UserEntity;
import com.pritam.repository.UserRepository;
import com.pritam.request.AddProductRequest;
import com.pritam.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final UserRepository userRepository;

    public ProductController(ProductService productService,
                             UserRepository userRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @GetMapping("/find-all-product/{userId}")
    public ResponseEntity<List<ProductEntity>> getAllProducts(@PathVariable Integer userId) {
        isAdmin(userId);
        List<ProductEntity> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}/{userId}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id, @PathVariable Integer userId) {
        isAdmin(userId);
        Optional<ProductEntity> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-product/{userId}")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody AddProductRequest request, @PathVariable Integer userId) {
        isAdmin(userId);
        ProductEntity product = productService.addProduct(request);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}/{userId}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id,
                                                       @RequestBody AddProductRequest productRequest,
                                                       @PathVariable Integer userId) {
        isAdmin(userId);
        ProductEntity updatedProduct = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}/{userId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @PathVariable Integer userId) {
        isAdmin(userId);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    public void isAdmin(Integer userId) {
        UserEntity byId = userRepository.findById(userId)
                .orElseThrow(() -> new BadCredentialsException("User Is Not Valid"));
        if (byId.getRole().contains("USER")){
            try {
                throw new AccessDeniedException("User don't have enough privilege!");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
