package com.restaurant.service;

import com.restaurant.model.*;
import java.util.List;

public class MenuService {
    private final Menu menu;
    private final DataStore ds;

    public MenuService(Menu menu, DataStore ds) {
        this.menu = menu;
        this.ds = ds;
    }

    public List<MenuItem> listAll() { return menu.listAll(); }
    public List<MenuItem> listByCategory(String category) { return menu.listByCategory(category); }

    public boolean adjustPrice(String itemName, double newPrice) {
        ds.updateMenuItemPrice(itemName, newPrice);
        return menu.adjustPrice(itemName, newPrice);
    }

    public MenuItem findByName(String itemName) {
        for (MenuItem m : menu.listAll()) if (m.getName().equals(itemName)) return m;
        return null;
    }
}
