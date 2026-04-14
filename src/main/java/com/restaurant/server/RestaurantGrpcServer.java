package com.restaurant.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.DataStore;

import java.io.IOException;

public class RestaurantGrpcServer {
    private final int port;
    private final DataStore dataStore;
    private final MenuService menuService;
    private final OrderService orderService;
    private Server server;

    public RestaurantGrpcServer(int port) {
        this.port = port;
        this.dataStore = new DataStore();
        this.menuService = new MenuService(dataStore.getMenu(), dataStore);
        this.orderService = new OrderService(dataStore);
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new ManagerGrpcImpl(menuService, dataStore, orderService))
                .addService(new ServerGrpcImpl(menuService, orderService, dataStore))
                .addService(new ChefGrpcImpl(orderService, menuService, dataStore))
                .build()
                .start();

        System.out.println("Restaurant gRPC Server started on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                RestaurantGrpcServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        RestaurantGrpcServer server = new RestaurantGrpcServer(port);
        server.start();
        server.blockUntilShutdown();
    }
}
