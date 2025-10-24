package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping("/new")
    public String productForm(Model model) {
        model.addAttribute("product", new ProductDto());
        return "admin/product_form";
    }

    @PostMapping("/new")
    public String save(ProductDto dto) {
        productService.save(dto);
        return "redirect:/admin/product/list";
    }

    @GetMapping("/list")
    public String productList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product_list";
    }
}
