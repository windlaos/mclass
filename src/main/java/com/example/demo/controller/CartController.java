package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<Product> cart = (List<Product>) session.getAttribute("CART");
        if (cart == null) cart = new ArrayList<>();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("CART");
        if (cart == null) cart = new ArrayList<>();

        Product product = productService.findById(id);
        cart.add(product);

        session.setAttribute("CART", cart);
        return "redirect:/cart";
    }
}
