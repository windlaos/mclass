package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart/add/{id}")
    public String addToCart() {
        // ✅ 장바구니 기능은 나중에 구현 예정 → 일단 목록으로 이동
        return "redirect:/";
    }
}
