package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    // 장바구니 목록
    @GetMapping("/cart")
    public String cart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        int totalPrice = cartService.getTotalPrice();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }

    // 담기 (수량 +1)
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        var product = productService.findById(id);
        cartService.addItem(product, 1);
        return "redirect:/cart";
    }

    // 삭제
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }
}
