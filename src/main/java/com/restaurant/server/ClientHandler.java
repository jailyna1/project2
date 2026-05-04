// package com.restaurant.server;

// import com.restaurant.service.DataStore;
// import com.restaurant.service.MenuService;
// import com.restaurant.service.OrderService;
// import com.restaurant.service.UserService;


// import java.io.*;
// import java.net.Socket;

// public class ClientHandler implements Runnable {

//     private final Socket socket;
//     private final DataStore dataStore;
//     private final MenuService menuService;
//     private final OrderService orderService;
//     private final UserService userService;


//     public ClientHandler(Socket socket, DataStore ds, MenuService menuService, OrderService orderService, UserService userService) {
//         this.socket = socket;
//         this.dataStore = ds;
//         this.menuService = menuService;
//         this.orderService = orderService;
//         this.userService = userService;
//     }

//     @Override
//     public void run() {
//         try (
//             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
//         ) {
//             System.out.println("Client connected: " + socket.getRemoteSocketAddress());

//             ServerSession serverSession = new ServerSession(menuService, orderService, dataStore, userService);
//             ManagerSession managerSession = new ManagerSession(menuService, dataStore, orderService, userService);
//             ChefSession chefSession = new ChefSession(orderService, menuService, dataStore, userService);

//             Object currentSession = null;

//             out.write("WELCOME TO RESTAURANT X! Please LOGIN with: LOGIN <user> <pass>");
//             out.newLine();
//             out.flush();

//             String line;
//             while ((line = in.readLine()) != null) {

//                 line = line.trim();
//                 if (line.isEmpty()) continue;

//                 String response;

//                 if (currentSession == null) {

//                     if (!line.toUpperCase().startsWith("LOGIN")) {
//                         response = "Please LOGIN first using: LOGIN <user> <pass>";
//                     } else {
//                         String[] parts = line.split("\\s+");
//                         if (parts.length != 3) {
//                             response = "ERR usage: LOGIN <user> <pass>";
//                         } else {
//                             String user = parts[1].toLowerCase();
//                             String pass = parts[2];

//                             switch (user) {
//                                 case "server":
//                                     response = serverSession.processCommand(line);
//                                     if (serverSession.isAuthenticated()) {
//                                         currentSession = serverSession;
//                                     }
//                                     break;

//                                 case "manager":
//                                     response = managerSession.processCommand(line);
//                                     if (managerSession.isAuthenticated()) {
//                                         currentSession = managerSession;
//                                     }
//                                     break;

//                                 case "chef":
//                                     response = chefSession.processCommand(line);
//                                     if (chefSession.isAuthenticated()) {
//                                         currentSession = chefSession;
//                                     }
//                                     break;

//                                 default:
//                                     response = "ERR unknown user role";
//                             }
//                         }
//                     }

//                 } else {
//                     if (currentSession == serverSession) {
//                         response = serverSession.processCommand(line);
//                     } else if (currentSession == managerSession) {
//                         response = managerSession.processCommand(line);
//                     } else {
//                         response = chefSession.processCommand(line);
//                     }


//                     if ("LOGOUT".equalsIgnoreCase(response)) {
//                         currentSession = null;
//                     }

//                     if ("EXIT".equalsIgnoreCase(response)) {
//                         out.write(response);
//                         out.newLine();
//                         out.flush();
//                         break;
//                     }
//                 }


//                 if (response != null && !response.isEmpty()) {
//                     out.write(response);
//                     out.newLine();
//                     out.flush();
//                 }
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//             try { socket.close(); } catch (IOException ignored) {}
//             System.out.println("Client disconnected.");
//         }
//     }
// }
