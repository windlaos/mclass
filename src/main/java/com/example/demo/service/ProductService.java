package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

import java.util.List;

@Service
public class ProductService {
    public List<Product> getProducts() {
        return List.of(
            new Product(1L, "상품1", "/images/product1.jpg", 20000),
            new Product(2L, "상품2", "/images/product2.jpg", 30000)
        );
    }

    public Product getProductById(Long id) {
        return getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}