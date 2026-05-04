package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Type { DINE_IN, TAKE_OUT }

    private static int nextId = 1;

    private final int id;
    private final Type type;
    private static int tableNumber;
    private int guests;
    private String customerName;
    private String status;
    private boolean notified;
    private final List<OrderLine> lines = new ArrayList<>();

    private int counter = 0;

    public Order(Type type) {
        this.id = nextId++;
        this.type = type;
        this.status = "PENDING";
        this.notified = false;
    }

    public int getId()                    { return id; }
    public Type getType()                 { return type; }
    public String getStatus()             { return status; }
    public void setStatus(String s)       { this.status = s; }
    public int getTableNumber()           { return tableNumber; }
    public static void setTableNumber(int t)     { tableNumber = t; }
    public void setTable(int c) { 
        counter  = c;
        Order.setTableNumber(counter); 
        counter++;
    }
    public int getGuests()                { return guests; }
    public void setGuests(int guests2)          { this.guests = guests2; }
    public String getCustomerName()       { return customerName; }
    public void setCustomerName(String n) { this.customerName = n; }
    public void setCustomer(String n)     { this.customerName = n; }
    public boolean isNotified()           { return notified; }
    public void setNotified(boolean n)    { this.notified = n; }
    public void setReady()                { this.status = "READY"; }  
    public List<OrderLine> getLines()     { return lines; }
    public void addLine(OrderLine l)      { lines.add(l); }
    public double getTotal() {
        double total = 0.0;
        for (OrderLine l : lines) {
            total += l.getItem().getPriceValue();
        }
        return total;
    }


    @Override
    public String toString() {
        return "Order #" + id + "[" + type + "|" + status + "]";
    }
}

