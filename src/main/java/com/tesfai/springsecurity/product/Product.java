package com.tesfai.springsecurity.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "PRODUCTS_TABLE")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
