package com.springbootreactive.springbootreactive.domain;

public class CartItem {
    private Item item;
    private int quantity;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    private CartItem() {}

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increment() {
        this.quantity += 1;
    }
}
