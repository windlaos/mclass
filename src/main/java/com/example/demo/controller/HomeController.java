package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.service.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) { // ✅ 생성자 주입 권장
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }
}

