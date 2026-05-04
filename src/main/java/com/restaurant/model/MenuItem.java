package com.restaurant.model;

public class MenuItem {
    private String name;
    private String category;
    private double price;

    public MenuItem(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return String.format("%.2f", price); }
    public double getPriceValue() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return category + " | " + name + " : $" + price;
    }
}
