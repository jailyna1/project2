package com.restaurant.model;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class User {
    public enum Role { MANAGER, SERVER, CHEF }

    @SerializedName("user")
    private String username;
    
    @SerializedName("pass")
    private String password;
    
    @SerializedName("role")
    private Role role;

    // No-arg constructor for Gson deserialization
    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role=" + getRole() +
                '}';
    }
}
