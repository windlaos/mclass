package com.example.demo.model;

public class CartItem {
    private Long id;
    private String name;
    private String imageUrl;
    private int price;
    private int quantity = 1;

    public CartItem(Long id, String name, String imageUrl, int price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 1) this.quantity--;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
