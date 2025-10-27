package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public int getTotalPrice() {
        return cartItems.stream()
                .mapToInt(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }
}
