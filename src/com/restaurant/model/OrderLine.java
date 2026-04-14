package com.restaurant.model;

public class OrderLine {
    private final MenuItem item;
    private final int quantity;

    public OrderLine(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() { return item; }
    public int getQty() { return quantity; }

    @Override
    public String toString() {
        return quantity + "x " + item.getName() + " ($" + item.getPrice() + ")";
    }
}
