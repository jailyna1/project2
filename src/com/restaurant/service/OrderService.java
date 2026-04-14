package com.restaurant.service;

import com.restaurant.model.*;

import java.util.List;

public class OrderService {
    private final DataStore ds;

    public OrderService(DataStore ds) {
        this.ds = ds;
    }

    public Order createDineInOrder(String customer) {
        Order o = new Order(Order.Type.DINE_IN);
        o.setCustomer(customer);
        ds.getOrders().add(o);
        ds.saveOrders();
        return o;
    }

    public Order createTakeOutOrder(String customer) {
        Order o = new Order(Order.Type.TAKE_OUT);
        o.setCustomer(customer);
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
        if (o == null) return "Order not found";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Bill for Order #%d:", o.getId()));
    
        for (OrderLine l : o.getLines()) {
            sb.append(String.format(" %s - $%.2f;", l.getItem().getName(), l.getItem().getPrice()));
        }
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
        o.setNotified(true);
        o.setReady();
        ds.saveOrders();
        return true;
    }
}
