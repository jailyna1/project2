package com.restaurant.server;

import com.google.gson.Gson;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.service.MenuService;
import com.restaurant.service.DataStore;
import com.restaurant.service.OrderService;
import java.io.PrintStream;

import java.io.IOException;
import java.util.List;

public class ManagerSession {

    private DataStore ds;
    private OrderService orderService;
    private final MenuService menuService;
    private final Gson gson = new Gson();

    private boolean authenticated = false;
    private String currentUser = null;

    public ManagerSession(MenuService menuService, DataStore ds , OrderService os) throws IOException {
        this.ds = ds;
        this.menuService = menuService;
        this.orderService = os;
    }

    public String processCommand(String line) {
        if (line == null) return null;

        line = line.trim();
        if (line.isEmpty()) return "";

        String[] parts = line.split("\\s+");
        String cmd = parts[0].toUpperCase();

        System.out.println("Received command: " + cmd);
            if(parts.length > 1) {
                for (int i = 1; i < parts.length; i++) {
                    System.out.println("Argument " + i + ": " + parts[i]);
                }
            } else {
                System.out.println("No arguments provided.");
            }

            switch (cmd) {
                case "LOGIN": 
                    String[] loginParts = line.split("\\s+");
                    if (loginParts.length != 3) {
                        System.out.println("LOGIN command received with incorrect number of arguments");
                        return "Error usage: LOGIN <user> <pass>";
                    }

                    String user = loginParts[1];
                    String pass = loginParts[2];

                    if (authenticate(user, pass)) {
                        authenticated = true;
                        System.out.println("Able to authenticate user as manager");
                        return "Logged in as MANAGER";
                    } else {
                        return "Error invalid credentials";
                    }
            

                case "LIST_MENU":
                    if (!authenticated) return "Error not authenticated. Use LOGIN first.";
                    if(parts.length != 1) {
                        System.out.println("LIST_MENU command received with incorrect number of arguments");
                        return "Error usage: LIST_MENU";
                    }

                    System.out.println("Retrieving menu items");
                    List<MenuItem> all = menuService.listAll();
                    System.out.println("Found " + all.size() + " menu items.");
                    System.out.println(gson.toJson(all));

                    StringBuilder str = new StringBuilder();
                    str.append("Menu Items: ");
                    for (MenuItem m : all) {
                        if ("Starters".equalsIgnoreCase(m.getCategory())) {
                            str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
                        }
                    }
                    for (MenuItem m : all) {
                        if ("Mains".equalsIgnoreCase(m.getCategory())) {
                            str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
                        }
                    }
                    for (MenuItem m : all) {
                        if ("Drinks".equalsIgnoreCase(m.getCategory())) {
                            str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
                        }
                    }
                    for (MenuItem m : all) {
                        if ("Desserts".equalsIgnoreCase(m.getCategory())) {
                            str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
                        }
                    }

                    if (str.length() > 3 && str.substring(str.length() - 3).equals(" | ")) {
                        str.setLength(str.length() - 3);
                    }

                    String allItem = str.toString();
                    return allItem;
                
                case "ADJUST_PRICE":
                    if (!authenticated) break;
                    if (parts.length != 3) {
                        System.out.println("ADJUST_PRICE command received with incorrect number of arguments");
                        return "Error usage: ADJUST_PRICE <itemName> <newPrice>";
                    }
                    String itemName = parts[1];
                    try {
                        System.out.println("Adjusting price for item --> " + itemName
                            + " to new price: " + parts[2]
                        );
                        double newPrice = Double.parseDouble(parts[2]);
                        boolean ok = menuService.adjustPrice(itemName, newPrice);
                        return ok ? "Price updated" : "Error item not found";
                    } catch (NumberFormatException e) {
                        return "Error invalid price";
                    }
                
                case "AVAILABLE_COMMANDS":
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Manager Command List: ").append(" LOGIN <user> <pass> |").append(" LIST_MENU |").append(" ADJUST_PRICE <item> <new_price> |")
                        .append(" LOGOUT |").append(" EXIT");
                        return sb.toString();

                    } catch (Exception e) {
                        return "Error retrieving commands";
                    }

                case "LOGOUT":
                    authenticated = false;
                    return "Logged out!";
                case "EXIT":             
                    authenticated = false;
                    System.out.println("Exiting manager session...");
                    System.exit(0);
            
                    return "Goodbye!";
                
                default:
                    return "Error unknown command";
            }
            return "Error not authenticated";
        }
    

    private boolean authenticate(String user, String pass) {
        return ("manager".equalsIgnoreCase(user) && "pass".equals(pass));
    }

    private boolean requireAuth(java.io.PrintStream out) {
        if (!authenticated) {
            out.println("Error not authenticated. Use LOGIN first.");
            return false;
        }
        return true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}