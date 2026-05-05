package com.restaurant.service;

import java.util.List;

import com.restaurant.model.User;
import com.restaurant.service.DataStore;


public class UserService {
    private final DataStore ds;

    public UserService(DataStore dataStore) {
        this.ds = dataStore;
    }

    public User authenticate(String username, String password) {
        for (User u : ds.getStaff()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        System.out.println("Authentication failed for user: " + username);
        return null;
    }

    public List<User> listAll() { 
        return ds.getStaff(); 
    }

    public void addEmployee(User u) { 
        ds.getStaff().add(u); 
        ds.saveStaff();
    }

    public User.Role getRole(String username) {
        for (User u : ds.getStaff()) {
            if (u.getUsername().equals(username)) {
                return u.getRole();
            }
        }
        return null;
    }

    public void removeEmployee(User u) { 
        ds.getStaff().remove(u); 
        ds.saveStaff();
    }
}
