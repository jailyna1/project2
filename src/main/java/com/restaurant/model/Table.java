package com.restaurant.model;

public class Table {
    private int tableNo;
    private int cap;
    private boolean isAvailable;

    public Table(int tableNo, int capacity, boolean isAvailable) {
        this.tableNo = tableNo;
        this.cap = capacity;
        this.isAvailable = isAvailable;
    }

    public int getTableNumber() {
        return tableNo;
    }

    public int getCapacity() {
        return cap;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
