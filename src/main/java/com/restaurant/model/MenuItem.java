package com.restaurant.model;

import java.util.List;

public class MenuItem {
    private String name;
    private String category;
    private double price;
    private List<String> recipe;

    public MenuItem(String name, String category, double price, List<String> recipe) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.recipe = recipe;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getPrice() { return String.format("%.2f", price); }
    public double getPriceValue() { return price; }
    public void setPrice(double price) { this.price = price; }
    public List<String> getRecipe() { return recipe; }
    public void setRecipe(List<String> recipe) { this.recipe = recipe; }


    @Override
    public String toString() {
        return category + " | " + name + " : $" + price;
    }
}
