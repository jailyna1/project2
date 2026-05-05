// package com.restaurant.server;

// import com.google.gson.Gson;
// import com.restaurant.model.MenuItem;
// import com.restaurant.model.Order;
// import com.restaurant.model.User;
// import com.restaurant.service.MenuService;
// import com.restaurant.service.OrderService;
// import com.restaurant.service.UserService;
// import com.restaurant.service.DataStore;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// public class ServerSession {

//     private final MenuService menuService;
//     private final OrderService orderService;
//     private final UserService userService;
//     private final DataStore dataStore;
//     private final Gson gson = new Gson();
//     private boolean authenticated = false;

//     public ServerSession(DataStore dataStore, MenuService menuService, OrderService os, UserService us) {
//         this.menuService = menuService;
//         this.orderService = os;
//         this.userService = us;
//         this.dataStore = dataStore;
//     }

//     public String processCommand(String line) {
//         if (line == null) return null;

//         line = line.trim();
//         if (line.isEmpty()) return "";

//         String[] parts = line.split("\\s+");
//         String cmd = parts[0].toUpperCase();

//         System.out.println("Received command: " + cmd);
//             if(parts.length > 1) {
//                 for (int i = 1; i < parts.length; i++) {
//                     System.out.println("Argument " + i + ": " + parts[i]);
//                 }
//             } else {
//                 System.out.println("No arguments provided.");
//             }
        

//         switch (cmd) {
//             case "LOGIN": {
//                 String[] loginParts = line.split("\\s+");
//                 if (loginParts.length != 3) {
//                     return "Error usage: LOGIN <user> <pass>";
//                 }

//                 String user = loginParts[1];
//                 String pass = loginParts[2];

//                 if (authenticate(user, pass)) {
//                     authenticated = true;
//                     System.out.println("Able to authenticate user");
//                     return "Logged in as SERVER";
//                 } else {
//                     return "Error invalid credentials. Cannot login.";
//                 }
//             }

//             case "LIST_MENU":
//                     if (!authenticated) return "Error not authenticated. Use LOGIN first.";
//                     if(parts.length != 1) {
//                         System.out.println("LIST_MENU command received with incorrect number of arguments");
//                         return "Error usage: LIST_MENU";
//                     }

//                     System.out.println("Retrieving menu items");
//                     List<MenuItem> all = menuService.listAll();
//                     System.out.println("Found " + all.size() + " menu items.");
//                     System.out.println(gson.toJson(all));

//                     StringBuilder str = new StringBuilder();
//                     str.append("Menu Items: ");
//                     for (MenuItem m : all) {
//                         if ("Starters".equalsIgnoreCase(m.getCategory())) {
//                             str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
//                         }
//                     }
//                     for (MenuItem m : all) {
//                         if ("Mains".equalsIgnoreCase(m.getCategory())) {
//                             str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
//                         }
//                     }
//                     for (MenuItem m : all) {
//                         if ("Drinks".equalsIgnoreCase(m.getCategory())) {
//                             str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
//                         }
//                     }
//                     for (MenuItem m : all) {
//                         if ("Desserts".equalsIgnoreCase(m.getCategory())) {
//                             str.append(m.getName()).append(" ($").append(m.getPrice()).append(") | ");
//                         }
//                     }
                    
//                     if (str.length() > 3 && str.substring(str.length() - 3).equals(" | ")) {
//                         str.setLength(str.length() - 3);
//                     }

//                     String allItem = str.toString();
//                     return allItem;

//             case "LIST_ORDERS":
//                     if (!authenticated) return "Error not authenticated. Use LOGIN first.";
//                     if(parts.length != 1) {
//                         System.out.println("LIST_ORDERS command received with incorrect number of arguments");
//                         return "Error usage: LIST_ORDERS";
//                     }

//                     System.out.println("Retrieving Orders");
//                     List<Order> currAll = orderService.listOrders();
//                     System.out.println("Found " + currAll.size() + " orders.");
//                     System.out.println(gson.toJson(currAll));

//                     StringBuilder str2 = new StringBuilder();
//                     str2.append("Orders: ");
//                     for (Order o : currAll) {
//                         StringBuilder orderDetails = new StringBuilder();
//                         for (int i = 0; i < o.getLines().size(); i++) {
//                             orderDetails.append(o.getLines().get(i).toString());
//                             if (i < o.getLines().size() - 1) {
//                                 orderDetails.append(", ");
//                             }
//                         }
//                         str2.append("Order #").append(o.getId()).append(": [").append(orderDetails).append("] - ").append(o.getStatus()).append(" | ");
//                     }
//                     if (str2.length() > 3 && str2.substring(str2.length() - 3).equals(" | ")) {
//                         str2.setLength(str2.length() - 3);
//                     }

//                     String allItem2 = str2.toString();
//                     return allItem2;

//             case "PLACE_TAKEOUT_ORDER":
//                 if (!authenticated) return "Error not authenticated. Use LOGIN first.";
//                 if (parts.length < 2 || parts.length > 12 || parts[1].trim().isEmpty()) {
//                     return "Error usage: PLACE_TAKEOUT_ORDER <customerName> <item1> <item2> ... (up to 10 items)";
//                 }
//                 String customerName = parts[1].trim();
//                 Order newOrd = orderService.createTakeOutOrder(customerName);
//                 newOrd.setCustomerName(newOrd.getCustomerName());
//                 for(int i = 2; i < parts.length; i++) {
//                     String itemName = parts[i].trim();
//                     MenuItem menuItem = menuService.findByName(itemName);
//                     if (menuItem != null) {
//                         System.out.println("Adding item to order " + newOrd.getId() + ": " + itemName);
//                         orderService.addOrderLine(newOrd.getId(), itemName);
//                     } else {
//                         System.out.println("Menu item not found: " + itemName);
//                         return "Error: Menu item '" + itemName + "' not found";
//                     }
//                 }
//                 return "Takeout order completed with ID #" + newOrd.getId();

//             case "PLACE_DINEIN_ORDER":
//                 if (!authenticated) return "Error not authenticated. Use LOGIN first.";
//                 String args = line.substring(line.indexOf(" ") + 1).trim(); 
//                 if (!args.startsWith("|") || args.split("\\|").length < 2) {
//                     return "Error usage: PLACE_DINEIN_ORDER | <customer_name1> <item1> <item2> ... | <customer_name2> <item1> <item2> ... | ... (up to 4 customers with 4 items each)";
//                 }
                

//                 String[] guestSections = args.split("\\|");
//                 List<Order> currOrders = new ArrayList<>();
//                 for (String section : guestSections) {
//                     section = section.trim();
//                     if (section.isEmpty()) continue; 
//                     System.out.println("CREATING AN ORDER");
//                     String[] guestParts = section.split("\\s+"); 
//                     if (guestParts.length < 2) {
//                         return "Error: Each guest section must have a customer name and at least one item.";
//                     }
                    
//                     String custName = guestParts[0];
//                     Order order = orderService.createDineInOrder(custName);
//                     currOrders.add(order);
                    
//                     for (int j = 1; j < guestParts.length; j++) {
//                         String itemName = guestParts[j];
//                         if (!orderService.addOrderLine(order.getId(), itemName)) {
//                             return "Error: Menu item '" + itemName + "' not found";
//                         }
//                     }
//                 }
                
//                 return "Dine-in orders completed with IDs: " + currOrders.stream().map(o -> "#" + o.getId()).collect(Collectors.joining(", "));

//             case "SHOW_BILL":
//                 if (!authenticated) return "Error not authenticated. Use LOGIN first.";
//                 if (parts.length != 2 || parts[1].trim().isEmpty()) {
//                     return "Error usage: SHOW_BILL <orderId>";
//                 }
//                 try {
//                     System.out.println("Searching for bill for ID" + parts[1].trim());
//                     int orderId = Integer.parseInt(parts[1].trim());

//                     return orderService.showBill(orderId);
                    
//                 } catch (Exception e) {
//                     return "Error usage: SHOW_BILL <orderId>";
//                 }
            
//             case "AVAILABLE_COMMANDS":
//                 if (parts[1].trim().isEmpty()) {
//                     return "Error usage: AVAILABLE_COMMANDS";
//                 }
//                 try {
//                     StringBuilder sb = new StringBuilder();
//                     sb.append("Available commands for SERVER:\n");
//                     sb.append("LIST_MENU\n");
//                     sb.append("LIST_ORDERS\n");
//                     sb.append("PLACE_TAKEOUT_ORDER <customer_name> <item1> <item2> ...\n");
//                     sb.append("PLACE_DINEIN_ORDER <table_number> <item1> <item2> ...\n");
//                     sb.append("SHOW_BILL <order_id>\n");
//                     sb.append("LOGOUT\n");
//                     sb.append("EXIT\n");
//                     return sb.toString();
                    
//                 } catch (Exception e) {
//                     return "Error usage: AVAILABLE_COMMANDS";
//                 }

//             case "LOGOUT":
//             case "EXIT":
//                 authenticated = false;
//                 return "Logged out!";

//             default:
//                 return "Error unknown command";
//         }
//     }

//     private boolean authenticate(String user, String pass) {
//         for (User u : userService.listAll()) {
//             if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     public boolean isAuthenticated() {
//         return authenticated;
//     }
// }
