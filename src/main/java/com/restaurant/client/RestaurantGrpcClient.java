package com.restaurant.client;

import com.restaurant.grpc.generated.CommandServiceGrpc;
import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class RestaurantGrpcClient {
    private final ManagedChannel channel;
    private final CommandServiceGrpc.CommandServiceBlockingStub stub;

    public RestaurantGrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = CommandServiceGrpc.newBlockingStub(channel);
    }

    public void start() {
        System.out.println("WELCOME TO RESTAURANT X. Please LOGIN with: LOGIN <user> <pass>");
        System.out.println("Available commands after login:");
        System.out.println("- Server: LISTMENU, PLACEORDER, LISTORDERS, SHOWBILL, LOGOUT");
        System.out.println("- Manager: LISTMENU, ADJUSTPRICE, LOGOUT");
        System.out.println("- Chef: LISTORDERS, MARKREADY, LOGOUT");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String line;

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("EXIT") || line.equalsIgnoreCase("QUIT")) {
                break;
            }

            if (!line.isEmpty()) {
                try {
                    CommandRequest request = CommandRequest.newBuilder()
                            .setCommand(line)
                            .build();

                    CommandResponse response = stub.sendCommand(request);
                    System.out.println(response.getResponse());

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }

        shutdown();
    }

    public void shutdown() {
        channel.shutdown();
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        RestaurantGrpcClient client = new RestaurantGrpcClient(host, port);
        client.start();
    }
}

// package com.restaurant.client;

// import io.grpc.Channel;
// import io.grpc.ManagedChannel;
// import io.grpc.ManagedChannelBuilder;
// import com.restaurant.grpc.generated.*;

// import java.util.Scanner;
// import java.util.concurrent.TimeUnit;

// public class RestaurantGrpcClient {
//     private final ManagedChannel channel;
//     private ManagerServiceGrpc.ManagerServiceBlockingStub managerStub;
//     private ServerServiceGrpc.ServerServiceBlockingStub serverStub;
//     private ChefServiceGrpc.ChefServiceBlockingStub chefStub;
//     private String currentRole = null;

//     public RestaurantGrpcClient(String host, int port) {
//         channel = ManagedChannelBuilder.forAddress(host, port)
//                 .usePlaintext()
//                 .build();
//     }

//     public void shutdown() throws InterruptedException {
//         channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//     }

//     public void start() {
//         System.out.println("Welcome to Restaurant Service");
//         System.out.println("Use: LOGIN <role> <pass> where role is: manager, server, or chef");

//         try (Scanner scanner = new Scanner(System.in)) {
//             while (true) {
//                 System.out.print(">> ");
//                 String input = scanner.nextLine().trim();

//                 if (input.isEmpty()) continue;

//                 String[] parts = input.split("\\s+");
//                 String command = parts[0].toUpperCase();

//                 String response;

//                 if (currentRole == null) {
//                     if (!command.equals("LOGIN")) {
//                         System.out.println("Please LOGIN first using: LOGIN <role> <pass>");
//                         continue;
//                     }

//                     if (parts.length != 3) {
//                         System.out.println("Usage: LOGIN <role> <pass>");
//                         continue;
//                     }

//                     String role = parts[1].toLowerCase();
//                     String pass = parts[2];

//                     response = handleLogin(role, pass);
//                     System.out.println("SERVER: " + response);

//                     if (response.contains("Logged in")) {
//                         currentRole = role;
//                         managerStub = ManagerServiceGrpc.newBlockingStub(channel);
//                         serverStub = ServerServiceGrpc.newBlockingStub(channel);
//                         chefStub = ChefServiceGrpc.newBlockingStub(channel);
//                     }
//                 } else {
//                     response = handleCommand(currentRole, input);
//                     System.out.println("SERVER: " + response);

//                     if (command.equals("LOGOUT")) {
//                         currentRole = null;
//                     } else if (command.equals("EXIT")) {
//                         break;
//                     }
//                 }
//             }
//         }
//     }

//     private String handleLogin(String role, String pass) {
//         LoginRequest request = LoginRequest.newBuilder()
//                 .setUser(role)
//                 .setPass(pass)
//                 .build();

//         try {
//             switch (role) {
//                 case "manager":
//                     managerStub = ManagerServiceGrpc.newBlockingStub(channel);
//                     LoginResponse response = managerStub.login(request);
//                     return response.getMessage();
//                 case "server":
//                     serverStub = ServerServiceGrpc.newBlockingStub(channel);
//                     response = serverStub.login(request);
//                     return response.getMessage();
//                 case "chef":
//                     chefStub = ChefServiceGrpc.newBlockingStub(channel);
//                     response = chefStub.login(request);
//                     return response.getMessage();
//                 default:
//                     return "Error: Unknown role";
//             }
//         } catch (Exception e) {
//             return "Error: " + e.getMessage();
//         }
//     }

//     private String handleCommand(String role, String input) {
//         String[] parts = input.split("\\s+");
//         String command = parts[0].toUpperCase();

//         try {
//             switch (role) {
//                 case "manager":
//                     return handleManagerCommand(command, parts, input);
//                 case "server":
//                     return handleServerCommand(command, parts, input);
//                 case "chef":
//                     return handleChefCommand(command, parts, input);
//                 default:
//                     return "Error: Unknown role";
//             }
//         } catch (Exception e) {
//             return "Error: " + e.getMessage();
//         }
//     }

//     private String handleManagerCommand(String command, String[] parts, String input) {
//         switch (command) {
//             case "LIST_MENU":
//                 ListMenuResponse menuResponse = managerStub.listMenu(ListMenuRequest.newBuilder().build());
//                 StringBuilder sb = new StringBuilder("Menu Items: ");
//                 for (MenuItemMessage item : menuResponse.getItemsList()) {
//                     sb.append(item.getName()).append(" ($").append(item.getPrice()).append(") | ");
//                 }
//                 if (sb.length() > 3) {
//                     sb.setLength(sb.length() - 3);
//                 }
//                 return sb.toString();

//             case "ADJUST_PRICE":
//                 if (parts.length < 3) {
//                     return "Usage: ADJUST_PRICE <itemName> <newPrice>";
//                 }
//                 try {
//                     double newPrice = Double.parseDouble(parts[2]);
//                     AdjustPriceResponse response = managerStub.adjustPrice(
//                             AdjustPriceRequest.newBuilder()
//                                     .setItemName(parts[1])
//                                     .setNewPrice(newPrice)
//                                     .build());
//                     return response.getMessage();
//                 } catch (NumberFormatException e) {
//                     return "Error: Invalid price format";
//                 }

//             case "LOGOUT":
//                 managerStub.logout(GenericResponse.newBuilder().build());
//                 return "Logged out!";

//             case "EXIT":
//                 managerStub.logout(GenericResponse.newBuilder().build());
//                 return "Goodbye!";

//             default:
//                 return "Error: Unknown command";
//         }
//     }

//     private String handleServerCommand(String command, String[] parts, String input) {
//         switch (command) {
//             case "LIST_MENU":
//                 ListMenuResponse menuResponse = serverStub.listMenu(ListMenuRequest.newBuilder().build());
//                 StringBuilder sb = new StringBuilder("Menu Items: ");
//                 for (MenuItemMessage item : menuResponse.getItemsList()) {
//                     sb.append(item.getName()).append(" ($").append(item.getPrice()).append(") | ");
//                 }
//                 if (sb.length() > 3) {
//                     sb.setLength(sb.length() - 3);
//                 }
//                 return sb.toString();

//             case "LIST_ORDERS":
//                 ListOrdersResponse ordersResponse = serverStub.listOrders(ListOrdersRequest.newBuilder().build());
//                 StringBuilder orderSb = new StringBuilder("Orders: ");
//                 for (OrderMessage order : ordersResponse.getOrdersList()) {
//                     orderSb.append("Order #").append(order.getId()).append(": [");
//                     for (int i = 0; i < order.getLinesList().size(); i++) {
//                         OrderLineMessage line = order.getLinesList().get(i);
//                         orderSb.append(line.getQuantity()).append("x ")
//                                 .append(line.getItem().getName()).append(" ($")
//                                 .append(line.getItem().getPrice()).append(")");
//                         if (i < order.getLinesList().size() - 1) {
//                             orderSb.append(", ");
//                         }
//                     }
//                     orderSb.append("] - ").append(order.getStatus()).append(" | ");
//                 }
//                 if (orderSb.length() > 3) {
//                     orderSb.setLength(orderSb.length() - 3);
//                 }
//                 return orderSb.toString();

//             case "PLACE_TAKEOUT_ORDER":
//                 if (parts.length < 3) {
//                     return "Usage: PLACE_TAKEOUT_ORDER <customerName> <item1> <item2> ...";
//                 }
//                 PlaceTakeoutOrderRequest.Builder takeoutBuilder = PlaceTakeoutOrderRequest.newBuilder()
//                         .setCustomerName(parts[1]);
//                 for (int i = 2; i < parts.length; i++) {
//                     takeoutBuilder.addItemNames(parts[i]);
//                 }
//                 PlaceTakeoutOrderResponse takeoutResponse = serverStub.placeTakeoutOrder(takeoutBuilder.build());
//                 return takeoutResponse.getMessage();

//             case "PLACE_DINEIN_ORDER":
//                 // Example: PLACE_DINEIN_ORDER | John Water Bread | Jane Burger | Bob Wine
//                 String guestStr = input.substring(input.indexOf("|") + 1);
//                 PlaceDineinOrderRequest.Builder dineinBuilder = PlaceDineinOrderRequest.newBuilder();

//                 String[] guestSections = guestStr.split("\\|");
//                 for (String section : guestSections) {
//                     section = section.trim();
//                     if (section.isEmpty()) continue;
//                     String[] guestParts = section.split("\\s+");
//                     if (guestParts.length < 2) {
//                         return "Error: Each guest must have a name and at least one item";
//                     }
//                     GuestOrder.Builder guestBuilder = GuestOrder.newBuilder()
//                             .setCustomerName(guestParts[0]);
//                     for (int i = 1; i < guestParts.length; i++) {
//                         guestBuilder.addItemNames(guestParts[i]);
//                     }
//                     dineinBuilder.addGuests(guestBuilder.build());
//                 }

//                 PlaceDineinOrderResponse dineinResponse = serverStub.placeDineinOrder(dineinBuilder.build());
//                 return dineinResponse.getMessage();

//             case "SHOW_BILL":
//                 if (parts.length != 2) {
//                     return "Usage: SHOW_BILL <orderId>";
//                 }
//                 try {
//                     int orderId = Integer.parseInt(parts[1]);
//                     ShowBillResponse billResponse = serverStub.showBill(
//                             ShowBillRequest.newBuilder().setOrderId(orderId).build());
//                     return billResponse.getBill();
//                 } catch (NumberFormatException e) {
//                     return "Error: Invalid order ID";
//                 }

//             case "LOGOUT":
//                 serverStub.logout(GenericResponse.newBuilder().build());
//                 return "Logged out!";

//             case "EXIT":
//                 serverStub.logout(GenericResponse.newBuilder().build());
//                 return "Goodbye!";

//             default:
//                 return "Error: Unknown command";
//         }
//     }

//     private String handleChefCommand(String command, String[] parts, String input) {
//         switch (command) {
//             case "LIST_ORDERS":
//                 ListOrdersResponse ordersResponse = chefStub.listOrders(ListOrdersRequest.newBuilder().build());
//                 StringBuilder orderSb = new StringBuilder("Orders: ");
//                 for (OrderMessage order : ordersResponse.getOrdersList()) {
//                     orderSb.append("Order #").append(order.getId()).append(": [");
//                     for (int i = 0; i < order.getLinesList().size(); i++) {
//                         OrderLineMessage line = order.getLinesList().get(i);
//                         orderSb.append(line.getQuantity()).append("x ")
//                                 .append(line.getItem().getName()).append(" ($")
//                                 .append(line.getItem().getPrice()).append(")");
//                         if (i < order.getLinesList().size() - 1) {
//                             orderSb.append(", ");
//                         }
//                     }
//                     orderSb.append("] - ").append(order.getStatus()).append(" | ");
//                 }
//                 if (orderSb.length() > 3) {
//                     orderSb.setLength(orderSb.length() - 3);
//                 }
//                 return orderSb.toString();

//             case "MARK_ORDER_READY":
//                 if (parts.length != 2) {
//                     return "Usage: MARK_ORDER_READY <orderId>";
//                 }
//                 try {
//                     int orderId = Integer.parseInt(parts[1]);
//                     GenericResponse response = chefStub.markOrderReady(
//                             ShowBillRequest.newBuilder().setOrderId(orderId).build());
//                     return response.getMessage();
//                 } catch (NumberFormatException e) {
//                     return "Error: Invalid order ID";
//                 }

//             case "LOGOUT":
//                 chefStub.logout(GenericResponse.newBuilder().build());
//                 return "Logged out!";

//             case "EXIT":
//                 chefStub.logout(GenericResponse.newBuilder().build());
//                 return "Goodbye!";

//             default:
//                 return "Error: Unknown command";
//         }
//     }

//     public static void main(String[] args) {
//         String host = "localhost";
//         int port = 5000;

//         if (args.length > 0) {
//             host = args[0];
//         }
//         if (args.length > 1) {
//             port = Integer.parseInt(args[1]);
//         }

//         RestaurantGrpcClient client = new RestaurantGrpcClient(host, port);
//         client.start();

//         try {
//             client.shutdown();
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
// }
