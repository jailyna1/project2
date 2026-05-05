package com.restaurant.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restaurant.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStore {
    private final Menu menu = new Menu();
    private final List<Order> orders = new ArrayList<>();
    private final List<User> staff = new ArrayList<>();
    private final Gson gson = new Gson();
    private final String dataDir = "data";

    public DataStore() {
        ensureDir();
        loadMenu();
        loadOrders();
        loadStaff();
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
        menu.addItem(new MenuItem("Bruschetta", "Starters", 6.50, Arrays.asList("bread", "tomato", "basil")));
        menu.addItem(new MenuItem("Caesar Salad", "Starters", 7.25, Arrays.asList("lettuce", "croutons", "parmesan", "caesar dressing")));
        menu.addItem(new MenuItem("Grilled Salmon", "Mains", 18.99, Arrays.asList("salmon", "lemon", "dill")));
        menu.addItem(new MenuItem("Steak", "Mains", 21.50, Arrays.asList("beef", "salt", "pepper")));
        menu.addItem(new MenuItem("Pasta Alfredo", "Mains", 14.75, Arrays.asList("pasta", "alfredo sauce")));
        menu.addItem(new MenuItem("Cheesecake", "Desserts", 6.00, Arrays.asList("cream cheese", "sugar", "eggs")));
        menu.addItem(new MenuItem("Chocolate Mousse", "Desserts", 6.50, Arrays.asList("chocolate", "cream", "eggs")));
        menu.addItem(new MenuItem("Coffee", "Drinks", 2.75, Arrays.asList("coffee beans", "water")));
        menu.addItem(new MenuItem("Lemonade", "Drinks", 3.25, Arrays.asList("lemons", "sugar", "water")));

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

    public boolean updateMenuItemPrice(String itemName, String newPrice) {
        for (MenuItem m : menu.listAll()) {
            if (m.getName().equals(itemName)) {
                try {
                    m.setPrice(Double.parseDouble(newPrice));
                } catch (NumberFormatException e) {
                    return false;
                }
                saveMenu();
                return true;
            }
        }
        return false;
    }

    private void loadStaff() {
        File f = new File(dataDir, "staff.json");
        if (!f.exists()) {
            seedStaff();
            saveStaff();
            return ;
        }
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<List<User>>(){}.getType();
            List<User> list = gson.fromJson(r, type);
            if (list != null) staff.addAll(list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load staff", e);
        }
    }

    private void seedStaff() {
        staff.add(new User("managerCurr", "password", User.Role.MANAGER));
        staff.add(new User("serverCurr", "password", User.Role.SERVER));
        staff.add(new User("chefCurr", "password", User.Role.CHEF));
    }

    public void saveStaff() {
        try (Writer s = new FileWriter(new File(dataDir, "staff.json"))) {
            s.write(gson.toJson(staff));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save staff", e);
        }
    }

    public Menu getMenu() { 
        return menu; 
    }
    public List<Order> getOrders() { 
        return orders; 
    }

    public List<User> getStaff() { 
        return staff;
    }

    public static DataStore getInstance() {
        DataStore instance = new DataStore();
        return instance;
    }
}
