package com.example.demo.controller;

import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";  // index.html
    }

    @GetMapping("/company")
    public String company() {
        return "company"; // templates/company.html
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy"; // templates/privacy.html
    }
}
