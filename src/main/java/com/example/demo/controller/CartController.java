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

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id) {
        System.out.println("장바구니 담기 성공: " + id);
        return "redirect:/";
    }      
}
