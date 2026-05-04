package com.restaurant.server;

import com.google.gson.Gson;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.service.MenuService;
import com.restaurant.service.DataStore;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import com.restaurant.model.User;
import java.io.PrintStream;

import java.io.IOException;
import java.util.List;

public class ManagerSession {

    private DataStore ds;
    private OrderService orderService;
    private final MenuService menuService;
    private final UserService userService;
    private final Gson gson = new Gson();

    private boolean authenticated = false;
    //private String currentUser = null;

    public ManagerSession(MenuService menuService, DataStore ds , OrderService os, UserService us) throws IOException {
        this.ds = ds;
        this.menuService = menuService;
        this.orderService = os;
        this.userService = us;
    }

    public String processCommand(String line) {
        if (line == null) return null;

        line = line.trim();
        if (line.isEmpty()) return "";

        String[] parts = line.split("\\s+");
        String cmd = parts[0].toUpperCase();

        System.out.println("Authentication status: " + authenticated);
        System.out.println("Available commands: LIST_MENU, ADJUST_MENU, VIEW_STAFF, HIRE_EMPLOYEE, FIRE_EMPLOYEE, AVAILABLE_COMMANDS, LOGOUT, EXIT\n");
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
                        System.out.println("LOGIN command received with incorrect number of arguments.\n");
                        return "Error usage: LOGIN <user> <pass>\n";
                    }

                    String user = loginParts[1];
                    String pass = loginParts[2];


                    if (authenticate(user, pass) && userService.getRole(user) == User.Role.MANAGER) {
                        authenticated = true;
                        System.out.println("Able to authenticate user as manager. Client connected and manager session created.\n");
                        return "Logged in as MANAGER. Welcome!\n\n" + returnAvailableCommands();
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
                    System.out.println(gson.toJson(all) + "\n");

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
                            + " from " + menuService.findByName(itemName).getPrice() + " to new price: " + parts[2]
                        + "\n");
                        double newPrice = Double.parseDouble(parts[2]);
                        String newPriceStr = String.format("%.2f", newPrice);

                        boolean ok = menuService.adjustPrice(itemName, newPriceStr);
                        return ok ? "Price updated" : "Error: item not found";
                    } catch (NumberFormatException e) {
                        return "Error invalid price";
                    }

                // List all employees
                case "VIEW_STAFF":
                    if (!authenticated) break;

                    System.out.println("Retrieving menu items");
                    List<User> allStaff = userService.listAll();
                    System.out.println("Found " + allStaff.size() + " staff members.");
                    System.out.println(gson.toJson(allStaff) + "\n");


                    String staffList = "Viewing staff members: \n";
                    for (User u : userService.listAll()) {
                        staffList += "- " + u.getUsername() + " (" + u.getRole() + ")\n";
                    }
                    return staffList;

                // Hire an employee
                case "HIRE_EMPLOYEE":
                    if (!authenticated) break;
                    if (parts.length != 4) {
                        System.out.println("HIRE_EMPLOYEE command received with incorrect number of arguments");
                        return "Error usage: HIRE_EMPLOYEE <username> <password> <role>";
                    }
                    String username = parts[1];
                    String password = parts[2];
                    User.Role role = User.Role.valueOf(parts[3].toUpperCase());
                    User u = new User(username, password, role);
                    System.out.println("Hiring employee: " + username + " with role: " + role);
                    System.out.println("Adding employee to database: " + username);
                    userService.addEmployee(u);
                    return "Employee hired: " + username;
                
                    // Fire an employee
                case "FIRE_EMPLOYEE":
                    if (!authenticated) break;
                    if (parts.length != 2) {
                        System.out.println("FIRE_EMPLOYEE command received with incorrect number of arguments");
                        return "Error usage: FIRE_EMPLOYEE <username>";
                    }
                    String fireUsername = parts[1];
                    User fireUser = null;
                    for (User us : userService.listAll()) {
                        if (us.getUsername().equals(fireUsername)) {
                            System.out.println("Found employee to fire: " + fireUsername);
                            System.out.println("Removing employee from database: " + fireUsername);
                            fireUser = us;
                            break;
                        }
                    }
                    if (fireUser == null) {
                        return "Error: user not found";
                    }
                    userService.removeEmployee(fireUser);
                    return "Employee removed from database - no longer employed: " + fireUsername;

                case "AVAILABLE_COMMANDS":
                    try {
                        return returnAvailableCommands();

                    } catch (Exception e) {
                        return "Error retrieving commands";
                    }

                case "LOGOUT":
                    System.out.println("Client logged out. Manager session ended.");
                    authenticated = false;
                    System.out.println("Authenticated: " + authenticated + "\n");

                    break;

                case "EXIT":             
                    System.out.println("Client disconnected. Exiting manager session...");

                    authenticated = false;
                    System.exit(0);
            
                    return "Goodbye!";
                
                default:
                    return "Error unknown command";
            }
            return "Error: user not authenticated. Please LOGIN for user access.";
        }
    

    private boolean authenticate(String user, String pass) {
        User us = userService.authenticate(user, pass);

        if (us == null) {
            return false;
        }
        return true;
    }

    // private boolean requireAuth(java.io.PrintStream out) {
    //     if (!authenticated) {
    //         out.println("Error not authenticated. Use LOGIN first.");
    //         return false;
    //     }
    //     return true;
    // }

    private String returnAvailableCommands() {
        return "Available commands: \n- LIST_MENU\n- ADJUST_PRICE <item> <new_price>\n- VIEW_STAFF\n- HIRE_EMPLOYEE <user> <pass> <role>\n- FIRE_EMPLOYEE <user>\n- AVAILABLE_COMMANDS\n- LOGOUT\n- EXIT";
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}