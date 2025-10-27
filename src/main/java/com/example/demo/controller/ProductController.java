package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    // ✅ 상품 목록 (관리자)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product-list";
    }

    // ✅ 상품 등록 페이지
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product_form";
    }

    // ✅ 상품 등록 처리
    @PostMapping
    public String create(Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    // ✅ 상품 수정 페이지
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "admin/product_form";
    }

    // ✅ 상품 수정 처리
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Product updateProduct) {
        Product product = productService.findById(id);

        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setDescription(updateProduct.getDescription());
        product.setImageUrl(updateProduct.getImageUrl());

        productService.save(product);

        return "redirect:/admin/products";
    }

    // ✅ 상품 삭제 처리
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
