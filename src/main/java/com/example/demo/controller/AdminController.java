package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final FileUploadService fileUploadService;

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product-list";
    }

    @GetMapping("/admin/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    @PostMapping("/admin/product/save")
    public String saveProduct(Product product,
                              @RequestParam("imageFile") MultipartFile imageFile) {

        if (!imageFile.isEmpty()) {
            String imageUrl = fileUploadService.saveImage(imageFile);
            product.setImageUrl(imageUrl);
        }

        productService.save(product);
        return "redirect:/admin/products";
    }
}

