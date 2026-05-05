package com.restaurant.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private final Map<String, MenuItem> items = new HashMap<>();

    public void addItem(MenuItem item) {
        items.put(item.getName(), item);
    }

    public MenuItem getItem(String name) {
        return items.get(name);
    }

    public MenuItem getItemIgnoreCase(String name) {
        for (MenuItem item : items.values()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public List<MenuItem> listAll() {
        return new ArrayList<>(items.values());
    }

    public List<MenuItem> listByCategory(String category) {
        List<MenuItem> out = new ArrayList<>();
        for (MenuItem m : items.values()) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                out.add(m);
            }
        }
        return out;
    }

    public boolean adjustPrice(String name, String newPrice) {
        MenuItem it = items.get(name);
        if (it == null) return false;
        try {
            it.setPrice(Double.parseDouble(newPrice));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
