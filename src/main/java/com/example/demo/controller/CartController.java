package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        System.out.println("Cart Add Clicked! productId=" + id);
        return "redirect:/"; // 임시 처리: 홈 이동
    }
}
