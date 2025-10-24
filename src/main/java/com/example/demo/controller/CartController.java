package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id);

        if (product != null) {
            session.setAttribute("cartProduct", product);
        }

        return "redirect:/";
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        model.addAttribute("cartProduct", session.getAttribute("cartProduct"));
        return "cart";
    }
}
