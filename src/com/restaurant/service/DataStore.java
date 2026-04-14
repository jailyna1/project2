package com.restaurant.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restaurant.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private final Menu menu = new Menu();
    private final List<Order> orders = new ArrayList<>();
    private final Gson gson = new Gson();
    private final String dataDir = "data";

    public DataStore() {
        ensureDir();
        loadMenu();
        loadOrders();
    }

    private void ensureDir() {
        File d = new File(dataDir);
        if (!d.exists()) d.mkdirs();
    }

    private void loadMenu() {
        File f = new File(dataDir, "menu.json");
        if (!f.exists()) {
            seedMenu();
            saveMenu();
            return;
        }
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<List<MenuItem>>(){}.getType();
            List<MenuItem> items = gson.fromJson(r, type);
            for (MenuItem m : items) menu.addItem(m);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu", e);
        }
    }

    private void seedMenu() {
        menu.addItem(new MenuItem("Bruschetta", "Starters", 6.50));
        menu.addItem(new MenuItem("Caesar Salad", "Starters", 7.25));
        menu.addItem(new MenuItem("Grilled Salmon", "Mains", 18.99));
        menu.addItem(new MenuItem("Steak", "Mains", 21.50));
        menu.addItem(new MenuItem("Pasta Alfredo", "Mains", 14.75));
        menu.addItem(new MenuItem("Cheesecake", "Desserts", 6.00));
        menu.addItem(new MenuItem("Chocolate Mousse", "Desserts", 6.50));
        menu.addItem(new MenuItem("Coffee", "Drinks", 2.75));
        menu.addItem(new MenuItem("Lemonade", "Drinks", 3.25));

    }

    private void saveMenu() {
        try (Writer w = new FileWriter(new File(dataDir, "menu.json"))) {
            w.write(gson.toJson(menu.listAll()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save menu", e);
        }
    }

    private void loadOrders() {
        File f = new File(dataDir, "orders.json");
        if (!f.exists()) return;
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<List<Order>>(){}.getType();
            List<Order> list = gson.fromJson(r, type);
            if (list != null) orders.addAll(list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load orders", e);
        }
    }

    public void saveOrders() {
        try (Writer w = new FileWriter(new File(dataDir, "orders.json"))) {
            w.write(gson.toJson(orders));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save orders", e);
        }
    }

    public void updateMenuItemPrice(String itemName, double newPrice) {
        for (MenuItem m : menu.listAll()) {
            if (m.getName().equals(itemName)) {
                m.setPrice(newPrice);
                saveMenu();
                return;
            }
        }
    }

    public Menu getMenu() { 
        return menu; 
    }
    public List<Order> getOrders() { 
        return orders; 
    }

    public static DataStore getInstance() {
        DataStore instance = new DataStore();
        return instance;
    }
}
