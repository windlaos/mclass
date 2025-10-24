package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        var product = productService.getProductById(id);

        boolean exists = false;
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.increaseQuantity();
                exists = true;
                break;
            }
        }

        if (!exists) {
            cart.add(new CartItem(product.getId(), product.getName(), product.getImageUrl(), product.getPrice()));
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/cart/increase/{id}")
    public String increase(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.increaseQuantity();
                break;
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease/{id}")
    public String decrease(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.decreaseQuantity();
                break;
            }
        }
        return "redirect:/cart";
    }
}
