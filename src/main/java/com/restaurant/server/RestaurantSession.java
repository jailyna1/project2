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

public class RestaurantSession {

    private DataStore ds;
    private OrderService orderService;
    private final RestaurantGrpcServer server;
    private final MenuService menuService;
    private final UserService userService;
    private final Gson gson = new Gson();

    private boolean authenticated = false;
    private String currentUser = null;

    public RestaurantSession(RestaurantGrpcServer server, DataStore dataStore, MenuService menuService, OrderService os, UserService us) throws IOException {
        this.server = server;
        this.ds = dataStore;
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
        System.out.println("All available commands: LIST_MENU, LIST_ORDERS, PLACE_TAKEOUT_ORDER, PLACE_DINEIN_ORDER, SHOW_BILL, ADJUST_MENU, VIEW_STAFF, HIRE_EMPLOYEE, FIRE_EMPLOYEE, NOTIFY_ORDER_READY, VIEW_RECIPES, AVAILABLE_COMMANDS, LOGOUT, EXIT\n");
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


                    if (authenticate(user, pass)) {
                        authenticated = true;
                        System.out.println("Able to authenticate user as " + userService.getRole(user) + ". Client connected and " + userService.getRole(user) + " session created.\n");
                        currentUser = user;
                        return "Logged in as " + userService.getRole(user) + ". Welcome!\n\n" + returnAvailableCommands(userService.getRole(user));
                    } else {
                        return "Error invalid credentials";
                    }
            
                case "LIST_MENU":
                    //only authenticate users with MANAGER or SERVER roles
                    if(userService.getRole(currentUser) == User.Role.MANAGER ||userService.getRole(currentUser) == User.Role.SERVER) {
                        authenticated = true;

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
                    } else {
                        authenticated = false;
                        return "ERROR: not authenticated. Log into valid user account.";
                    }

                    case "LIST_ORDERS":
                        //only authenticate users with SERVER and CHEF roles
                        if (userService.getRole(currentUser) == User.Role.SERVER || userService.getRole(currentUser) == User.Role.CHEF) {
                            authenticated = true;

                            System.out.println("Authenticated: " + authenticated);

                            if(parts.length != 1) {
                                System.out.println("LIST_ORDERS command received with incorrect number of arguments");
                                return "Error usage: LIST_ORDERS";
                            }

                            System.out.println("Retrieving Orders");
                            List<Order> currAll = orderService.listOrders();
                            System.out.println("Found " + currAll.size() + " orders.");
                            System.out.println(gson.toJson(currAll));

                            StringBuilder str2 = new StringBuilder();
                            str2.append("Orders: ");
                            for (Order o : currAll) {
                                StringBuilder orderDetails = new StringBuilder();
                                for (int i = 0; i < o.getLines().size(); i++) {
                                    orderDetails.append(o.getLines().get(i).toString());
                                    if (i < o.getLines().size() - 1) {
                                        orderDetails.append(", ");
                                    }
                                }
                                str2.append("Order #").append(o.getId()).append(": [").append(orderDetails).append("] - ").append(o.getStatus()).append(" | ");
                            }
                            if (str2.length() > 3 && str2.substring(str2.length() - 3).equals(" | ")) {
                                str2.setLength(str2.length() - 3);
                            }

                            String allItem2 = str2.toString();
                            return allItem2;
                        } else {
                            authenticated = false;
                            return "ERROR: not authorized. Log into valid user account.";
                        }
                    

                //place takout

                //place dinein
                
                case "ADJUST_PRICE":
                    //only allow MANAGER role to adjust prices
                    if(userService.getRole(currentUser) == User.Role.MANAGER) {
                        authenticated = true;

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
                    }else {
                        authenticated = false;
                        return "ERROR: not authenticated. Log into valid user account.";
                    }

                // List all employees
                case "VIEW_STAFF":
                    //only allow MANAGER to view staff
                    if (userService.getRole(currentUser) != User.Role.MANAGER) {
                        authenticated = false;
                        return "ERROR: not authenticated. Log into valid user account.";
                    };
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
                    //only allow MANAGER to hire employees
                    if (userService.getRole(currentUser) != User.Role.MANAGER) {
                        authenticated = false;
                        return "ERROR: not authenticated. Log into valid user account.";
                    }
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
                    //only allow MANAGER to fire employees
                    if (userService.getRole(currentUser) != User.Role.MANAGER) {
                        authenticated = false;
                        return "ERROR: not authenticated. Log into valid user account.";
                    }
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

                    //notify order ready
                    case "NOTIFY_ORDER_READY":
                        //only allow CHEF to notify order ready
                        if (userService.getRole(currentUser) != User.Role.CHEF) {
                            authenticated = false;
                            return "ERROR: not authenticated. Log into valid user account.";
                        }
                        if (!authenticated) break;
                        if (parts.length != 2) {
                            return "Error usage: NOTIFY_ORDER_READY <orderId>";
                        }

                        try {
                            int oid = Integer.parseInt(parts[1]);
                            boolean ok = orderService.notifyReady(oid);
                            System.out.println("Notifying order " + oid + " ready: " + ok);
                            return ok ? "OK notified" : "Error not found";
                        } catch (Exception e) {
                            System.out.println("Failed to parse order ID: " + parts[1]);
                            return "Error invalid orderId";
                        }

                    //view recipes - update menu database
                    case "VIEW_RECIPE":
                        //only allow CHEF to view recipes
                        if (userService.getRole(currentUser) != User.Role.CHEF) {
                            authenticated = false;
                            return "ERROR: not authenticated. Log into valid user account.";
                        }
                        if (!authenticated) break;
                        if (parts.length != 2) {
                            return "Error usage: VIEW_RECIPE <item name>";
                        }
                        return menuService.getRecipe(parts[1]);

                    case "SHOW_BILL":
                        //only server access
                        if (userService.getRole(currentUser) != User.Role.SERVER) {
                            authenticated = false;
                            return "ERROR: not authenticated. Log into valid user account.";
                        }
                        if (!authenticated) break;

                        if (parts.length != 2 || parts[1].trim().isEmpty()) {
                            return "Error usage: SHOW_BILL <orderId>";
                        }
                        try {
                            int orderId = Integer.parseInt(parts[1].trim());
                            System.out.println("Searching for bill with ID " + orderId);

                            return orderService.showBill(orderId);
                            
                        } catch (Exception e) {
                            return "Error usage: " + e.getMessage();
                        }

                case "AVAILABLE_COMMANDS":
                    try {
                        return returnAvailableCommands(userService.getRole(currentUser));

                    } catch (Exception e) {
                        return "Error retrieving commands";
                    }

                case "LOGOUT":
                    System.out.println("Client logged out. " + userService.getRole(currentUser) + " session ended.");
                    authenticated = false;
                    currentUser = null;
                    System.out.println("Authenticated: " + authenticated + "\n");

                    break;

                case "EXIT":             
                    System.out.println("Client disconnected. Exiting server...");

                    authenticated = false;
                    currentUser = null;
                    new Thread(() -> {
                        try {
                            server.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }).start();

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

    private String returnAvailableCommands(User.Role role) {
        switch (role) {
            case MANAGER:
                return "Available commands: \n- LIST_MENU\n- ADJUST_PRICE <item> <new_price>\n- VIEW_STAFF\n- HIRE_EMPLOYEE <user> <pass> <role>\n- FIRE_EMPLOYEE <user>\n- AVAILABLE_COMMANDS\n- LOGOUT\n- EXIT";
            case SERVER:
                return "Available commands: \n- LIST_MENU\n- LIST_ORDERS\n- PLACE_TAKEOUT_ORDER <customer_name> <item1> <item2> ...\n- PLACE_DINEIN_ORDER <table_number> <item1> <item2> ...\n- SHOW_BILL <order_id>\n- LOGOUT\n- EXIT";
            case CHEF:
                return "Available commands: \n- LIST_ORDERS\n- NOTIFY_ORDER_READY <orderId>\n- VIEW_RECIPE <item_name>\n- LOGOUT\n- EXIT";
            default:
                return "Error: unknown role";
        }
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}