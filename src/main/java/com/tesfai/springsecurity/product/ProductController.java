package com.tesfai.springsecurity.product;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("/products")
public class ProductController {
    private ProductService service;
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(){
        return ResponseEntity.ok().body("Welcome to spring security - with spring boot V.3.0.1");
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAny`Authority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> getProduct(@PathVariable Long productId){
        return service.getProduct(productId);
    }


    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
}
