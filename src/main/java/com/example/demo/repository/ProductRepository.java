package com.example.demo.repository;

import com.example.demo.entity.Product; // ✅ 이걸로 변경
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
