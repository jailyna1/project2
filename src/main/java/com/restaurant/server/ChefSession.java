// package com.restaurant.server;

// import com.google.gson.Gson;
// import com.restaurant.model.MenuItem;
// import com.restaurant.model.Order;
// import com.restaurant.service.MenuService;
// import com.restaurant.service.UserService;
// import com.restaurant.service.DataStore;
// import com.restaurant.service.OrderService;
// import java.io.PrintStream;

// import java.io.IOException;
// import java.util.List;

// public class ChefSession {
//     private DataStore ds;
//     private OrderService orderService;
//     private final MenuService menuService;
//     private final UserService userService;
//     private final Gson gson = new Gson();

//     private boolean authenticated = false;

//     public ChefSession(OrderService os, MenuService menuService, DataStore dataStore, UserService us) throws IOException {
//         this.orderService = os;
//         this.menuService = menuService;
//         this.ds = dataStore;
//         this.userService = us;
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

//             switch (cmd) {
//                 case "LOGIN": 
//                     String[] loginParts = line.split("\\s+");
//                         if (loginParts.length != 3) {
//                             System.out.println("LOGIN command received with incorrect number of arguments");
//                             return "Error usage: LOGIN <user> <pass>";
//                         }

//                         String user = loginParts[1];
//                         String pass = loginParts[2];

//                         if (authenticate(user, pass)) {
//                             authenticated = true;
//                             System.out.println("Able to authenticate user as chef");
//                             return "Logged in as CHEF";
//                         } else {
//                             return "Error invalid credentials";
//                         }

//                 case "LIST_ORDERS":
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

//                 case "NOTIFY_ORDER_READY":
//                     if (!authenticated) break;
//                     if (parts.length != 2) {
//                         return "Error usage: NOTIFY_ORDER_READY <orderId>";
//                     }

//                     try {
//                         int oid = Integer.parseInt(parts[1]);
//                         boolean ok = orderService.notifyReady(oid);
//                         System.out.println("Notifying order " + oid + " ready: " + ok);
//                         return ok ? "OK notified" : "Error not found";
//                     } catch (Exception e) {
//                         System.out.println("Failed to parse order ID: " + parts[1]);
//                         return "Error invalid orderId";
//                     }
                
//                 case "AVAILABLE_COMMANDS":
//                     try {
//                         StringBuilder sb = new StringBuilder();
//                         sb.append("Available commands for CHEF:").append(" LIST_ORDERS |").append(" NOTIFY_ORDER_READY <orderId> |")
//                         .append(" LOGOUT |").append(" EXIT");

//                         return sb.toString();
                        
//                     } catch (Exception e) {
//                         return "Error usage: AVAILABLE_COMMANDS";
//                     }

//             case "LOGOUT":
//             case "EXIT":
//                 authenticated = false;
//                 return "Logged out!";

//             default:
//                 return "Error unknown command";
//             }
//             return "Error not authenticated";
//     }

//     private boolean authenticate(String user, String pass) {
//         return ("chef".equalsIgnoreCase(user) && "pass".equals(pass));
//     }

//     public boolean isAuthenticated() {
//         return authenticated;
//     }
    
// }
