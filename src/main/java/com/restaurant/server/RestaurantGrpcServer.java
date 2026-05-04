package com.restaurant.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import com.restaurant.service.DataStore;

import java.io.IOException;

public class RestaurantGrpcServer {
    private final int port;
    private final DataStore dataStore;
    private final MenuService menuService;
    private final OrderService orderService;
    private final UserService userService;
    private Server server;

    public RestaurantGrpcServer(int port) {
        this.port = port;
        this.dataStore = new DataStore();
        this.menuService = new MenuService(dataStore.getMenu(), dataStore);
        this.orderService = new OrderService(dataStore);
        this.userService = new UserService(dataStore);
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService((io.grpc.BindableService) new CommandGrpcImpl(menuService, dataStore, orderService, userService))
                .addService((io.grpc.BindableService) new ManagerGrpcImpl(menuService, dataStore, orderService, userService))
                .addService((io.grpc.BindableService) new ServerGrpcImpl(menuService, orderService, dataStore, userService))
                .addService((io.grpc.BindableService) new ChefGrpcImpl(orderService, menuService, dataStore, userService))
                .build()
                .start();

        System.out.println("Restaurant gRPC Server started on port " + port + ".\n");

        System.out.println("Registered users:");
        System.out.println(userService.listAll());

        System.out.println("Waiting for clients to connect...");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("--> Shutting down gRPC server - JVM is shutting down");
            try {
                RestaurantGrpcServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("--> Server shut down");
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
            System.out.println("Server stopped.");
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
            System.out.println("Server blocked until shutdown.");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 5000;  
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        RestaurantGrpcServer server = new RestaurantGrpcServer(port);
        server.start();
        server.blockUntilShutdown();
    }
}
