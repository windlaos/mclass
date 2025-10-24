package com.example.demo.controller;

import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        // TODO : 추후 세션 장바구니 기능 추가 예정
        System.out.println("장바구니 담기 성공: 상품 ID = " + id);

        return "redirect:/"; // ✅ 일단 메인으로 이동시키기
    }
}
