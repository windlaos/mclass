package com.example.demo.controller;

import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var product = productService.findById(id);

        if (product == null) {
            return "redirect:/"; // 상품 없을 경우 홈으로
        }

        model.addAttribute("product", product);
        return "product-detail"; // ✅ 뷰 존재해야 함!
    }
}
