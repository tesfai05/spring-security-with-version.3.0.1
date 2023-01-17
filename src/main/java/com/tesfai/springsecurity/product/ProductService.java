package com.tesfai.springsecurity.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private  ProductRepository repository;
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    public ResponseEntity<?> getProduct(Long productId) {
        return ResponseEntity.ok().body(repository.findById(productId));
    }

    public ResponseEntity<?> saveProduct(Product product) {
        return new ResponseEntity<>(repository.save(product), HttpStatus.CREATED);
    }
}
