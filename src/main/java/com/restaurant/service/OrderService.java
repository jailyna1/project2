package com.restaurant.service;

import java.util.List;
import java.util.Scanner;

import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderLine;
import com.restaurant.model.Ingredient;
import com.restaurant.model.Table;


public class OrderService {
    private final DataStore ds;
    Scanner sc;
    

    public OrderService(DataStore ds) {
        this.ds = ds;
        this.sc = new Scanner(System.in);

        int maxId = 0;
        if (ds.getOrders() != null) {
            for (Order o : ds.getOrders()) {
                if (o.getId() > maxId) {
                    maxId = o.getId();
                }
            }
        }
        Order.setNextId(maxId + 1);
    }

    public Order createDineInOrder(int tableNumber) {
        Order o = new Order(Order.Type.DINE_IN);
        o.setCustomerName("Table " + tableNumber);
        o.setTableNumber(tableNumber);
        ds.getOrders().add(o);
        ds.saveOrders();
        return o;
    }

    public Order createTakeOutOrder(String customer) {
        Order o = new Order(Order.Type.TAKE_OUT);
        o.setCustomerName(customer);
        ds.getOrders().add(o);
        ds.saveOrders();
        return o;
    }


    public String getCustomerOrder(int orderId){
        return findOrder(orderId).getCustomerName();
    }

    public boolean addOrderLine(int orderId, String itemName) {
        Order o = findOrder(orderId);
        if (o == null) return false;
        MenuItem item = ds.getMenu().getItem(itemName);
        if (item == null) {
            item = ds.getMenu().getItemIgnoreCase(itemName);
        }
        if (item == null) return false;
        o.addLine(new OrderLine(item, 1));
        ds.saveOrders();
        return true;
    }

    public Order findOrder(int id) {
        for (Order o : ds.getOrders()) if (o.getId() == id) return o;
        return null;
    }

    public Order getOrder(int id) {
        return findOrder(id);
    }

    public String showBill(int orderId) {
        Order o = findOrder(orderId);
        Double countTotal = 0.00;

        if (o == null) return "Order not found";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Bill for Order #%d:\n", o.getId()));
    
        for (OrderLine l : o.getLines()) {
            sb.append(String.format(" %s - $%s;", l.getItem().getName(), l.getItem().getPrice()));
            countTotal += l.getItem().getPriceValue();
            System.out.println("Adding item to bill: " + l.getItem().getName() + " - $" + l.getItem().getPrice() + " --> $" + countTotal);
        }
        sb.append(String.format(" \nTotal: $%.2f\nTotal with tax: $%.2f\nTotal with tax + 15%% tip: $%.2f\nTotal with tax + 20%% tip: $%.2f", countTotal, countTotal * 1.08, countTotal * 1.08 * 1.15, countTotal * 1.08 * 1.20 ));
        return sb.toString();
    }

    public List<Order> listOrders() {
        return ds.getOrders();
    }

    public List<Order> listReadyOrders() {
        return ds.getOrders().stream().filter(o -> o.getStatus().equals("READY")).toList();
    }

    public boolean notifyReady(int orderId) {
        Order o = findOrder(orderId);
        if (o == null) return false;
        if (o.isNotified()) {
            System.out.println("Order #" + o.getId() + " is already notified");
            return true;
        }
        if (!ingredientsAvailable(o.getId())) {
            System.out.println("Order #" + o.getId() + " cannot be marked ready: ingredients unavailable");
            return false;
        }

        // Decrement ingredient quantities
        for (OrderLine line : o.getLines()) {
            MenuItem item = line.getItem();
            for (String ingredientName : item.getRecipe()) {
                ds.updateIngredientQuantity(ingredientName,
                    ds.getIngredients().stream()
                        .filter(i -> i.getName().equalsIgnoreCase(ingredientName))
                        .findFirst()
                        .get()
                        .getQuantity() - 1);
            }
        }
        o.setNotified(true);
        o.setReady();
        ds.saveOrders();
        return true;
    }

    public boolean ingredientsAvailable(int orderId){
        Order o = findOrder(orderId);
        if (o == null) return false;
        for(MenuItem item : o.getLines().stream().map(OrderLine::getItem).toList()) {
            if (item == null) return false;
            for (String ingredientName : item.getRecipe()) {
                boolean found = false;
                for (Ingredient i : ds.getIngredients()) {
                    if (i.getName().equals(ingredientName)) {
                        if (i.getQuantity() <= 0) {
                            return false;
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) return false; // ingredient not in inventory
            }
        }

        return true;
    }

    public boolean checkoutOrder(int orderId) {
        Order o = findOrder(orderId);
        if (o == null) return false;
        if (!"READY".equalsIgnoreCase(o.getStatus())) return false;
        if (o.getType() == Order.Type.DINE_IN && o.getTableNumber() > 0) {
            freeTable(o.getTableNumber());
        }
        o.setStatus("COMPLETED");
        ds.saveOrders();
        return true;
    }

    public int findAvailableTable(int numCustomers) {
        for (Table t : ds.getTables()) {
            if (t.isAvailable() && t.getCapacity() >= numCustomers) {
                t.setAvailable(false);
                ds.saveTables();
                return t.getTableNumber();
            }
        }
        return -1; // no table available
    }

    public void freeTable(int tableNumber) {
        for (Table t : ds.getTables()) {
            if (t.getTableNumber() == tableNumber) {
                t.setAvailable(true);
                ds.saveTables();
                break;
            }
        }
    }
}
