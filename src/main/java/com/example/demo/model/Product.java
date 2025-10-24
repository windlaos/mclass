package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 필수(JPA용)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 자동 증가 PK

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private int price;

    // ✅ 기존 생성자 계속 사용 가능
    public Product(Long id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}
