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
public class AdminProductController {

    private final ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product_form";
    }

    @PostMapping
    public String create(Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "admin/product_form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Product updateProduct) {
        Product p = productService.findById(id);
        p.setName(updateProduct.getName());
        p.setPrice(updateProduct.getPrice());
        p.setDescription(updateProduct.getDescription());
        p.setImageUrl(updateProduct.getImageUrl());
        productService.save(p);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
