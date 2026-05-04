// package com.restaurant.server;

// import com.restaurant.service.ClientHandler;
// import com.restaurant.service.DataStore;
// import com.restaurant.service.MenuService;
// import com.restaurant.service.OrderService;

// import java.io.*;
// import java.net.ServerSocket;
// import java.net.Socket;

// public class RestaurantServer {
//     private final int port;
//     private final DataStore ds;
//     private final MenuService menuService;
//     private final OrderService os;

//     public RestaurantServer(int port) {
//         this.port = port;
//         this.ds = new DataStore();
//         this.menuService = new MenuService(ds.getMenu(), ds);
//         this.os = new OrderService(ds);
//     }

//     public void start() throws IOException {
//         try (ServerSocket serverSocket = new ServerSocket(port)) {
//             System.out.println("Restaurant Server listening on port " + port);
//             while (true) {
//                 Socket socket = serverSocket.accept();
//                 new Thread(new ClientHandler(socket, ds, menuService, os)).start();
//             }
//         }
//     }

//     public static void main(String[] args) throws Exception {
//         int port = 5000;
//         new RestaurantServer(port).start();

        
//     }
// }
