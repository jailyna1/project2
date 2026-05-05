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
    //private final Menu menu = new Menu();
    private final List<Order> orders = new ArrayList<>();
    private final List<User> staff = new ArrayList<>();
    private final List<Table> tables = new ArrayList<>();
    private final Menu menu = new Menu();
    private final List<Ingredient> ingredients = new ArrayList<>();

    private final Gson gson = new Gson();
    private final String dataDir = "data";

    public DataStore() {
        ensureDir();
        loadMenu();
        loadOrders();
        loadStaff();
        loadTables();
        loadIngredients();
    }

    private void ensureDir() {
        File d = new File(dataDir);
        if (!d.exists()) d.mkdirs();
    }

    private void loadMenu() {
        File f = new File(dataDir, "menu.json");
        if (!f.exists()) {
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

    public void saveStaff() {
        try (Writer s = new FileWriter(new File(dataDir, "staff.json"))) {
            s.write(gson.toJson(staff));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save staff", e);
        }
    }
    private void loadTables() {
        File f = new File(dataDir, "tables.json");
        if (!f.exists()) {
            seedTables();
            saveTables();
            return ;
        }
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<TablesWrapper>(){}.getType();
            TablesWrapper wrapper = gson.fromJson(r, type);
            if (wrapper != null && wrapper.getTables() != null) {
                tables.addAll(wrapper.getTables());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tables", e);
        }
    }

    private void seedTables() {
        tables.add(new Table(1, 4, true));
        tables.add(new Table(2, 2, true));
        tables.add(new Table(3, 4, true));
        tables.add(new Table(4, 2, true));
        tables.add(new Table(5, 3, true));
        tables.add(new Table(6, 4, true));
        tables.add(new Table(7, 2, true));
        tables.add(new Table(8, 4, true));
        tables.add(new Table(9, 3, true));
        tables.add(new Table(10, 2, true));
    }

    public void saveTables() {
        try (Writer t = new FileWriter(new File(dataDir, "tables.json"))) {
            TablesWrapper wrapper = new TablesWrapper(tables);
            t.write(gson.toJson(wrapper));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tables", e);
        }
    }

    private void loadIngredients() {
        File f = new File(dataDir, "ingredients.json");
        if (!f.exists()) {
            saveIngredients();
            return ;
        }
        try (Reader r = new FileReader(f)) {
            Type type = new TypeToken<List<Ingredient>>(){}.getType();
            List<Ingredient> list = gson.fromJson(r, type);
            if (list != null) ingredients.addAll(list);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load ingredients", e);
        }
    }

    private void saveIngredients() {
        try (Writer w = new FileWriter(new File(dataDir, "ingredients.json"))) {
            w.write(gson.toJson(ingredients));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save ingredients", e);
        }
    }

    public boolean updateIngredientQuantity(String ingredientName, int newQuantity) {
        for (Ingredient i : ingredients) {
            if (i.getName().equals(ingredientName)) {
                i.setQuantity(newQuantity);
                saveIngredients();
                return true;
            }
        }
        return false;
    }


    public List<Table> getTables() {
        return tables;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static DataStore getInstance() {
        DataStore instance = new DataStore();
        return instance;
    }

    private static class TablesWrapper {
        private List<Table> tables;

        public TablesWrapper(List<Table> tables) {
            this.tables = tables;
        }

        public List<Table> getTables() {
            return tables;
        }
    }

	public int getIngredientQuantity(String itemName) {
		for (Ingredient i : ingredients) {
			if (i.getName().equals(itemName)) {
				return i.getQuantity();
			}
		}
		return 0;
	}
}
