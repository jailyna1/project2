package com.restaurant.service;

import com.restaurant.model.*;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public UserService() {
        users.put("manager", new User("manager", "pass", User.Role.MANAGER));
        users.put("server", new User("server", "pass", User.Role.SERVER));
        users.put("chef", new User("chef", "pass", User.Role.CHEF));
    }

    public User authenticate(String username, String password) {
        User u = users.get(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
