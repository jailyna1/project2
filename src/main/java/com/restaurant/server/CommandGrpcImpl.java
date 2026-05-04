package com.restaurant.server;

import com.restaurant.grpc.generated.CommandServiceGrpc;
import com.restaurant.model.User;
import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;
import com.restaurant.service.DataStore;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import io.grpc.stub.StreamObserver;

public class CommandGrpcImpl extends CommandServiceGrpc.CommandServiceImplBase {

    private final DataStore dataStore;
    private final MenuService menuService;
    private final OrderService orderService;
    private final UserService userService;

    // Session management per client (simplified - in production you'd use proper session management)
    private final ThreadLocal<ServerSession> serverSession = new ThreadLocal<>();
    private final ThreadLocal<ManagerSession> managerSession = new ThreadLocal<>();
    private final ThreadLocal<ChefSession> chefSession = new ThreadLocal<>();
    private final ThreadLocal<Object> currentSession = new ThreadLocal<>();

    public CommandGrpcImpl(MenuService menuService, DataStore dataStore, OrderService orderService, UserService userService) {
        this.menuService = menuService;
        this.dataStore = dataStore;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public void sendCommand(CommandRequest request, StreamObserver<CommandResponse> responseObserver) {
        try {
            String command = request.getCommand().trim();
            String response = processCommand(command);

            CommandResponse commandResponse = CommandResponse.newBuilder()
                    .setResponse(response)
                    .build();

            responseObserver.onNext(commandResponse);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private String processCommand(String line) {
        try {
            if (line == null || line.trim().isEmpty()) {
                return "";
            }

            line = line.trim();
            String[] parts = line.split("\\s+");
            String cmd = parts[0].toUpperCase();

            // Initialize sessions if not already done
            if (serverSession.get() == null) {
                serverSession.set(new ServerSession(menuService, orderService, dataStore, userService));
                managerSession.set(new ManagerSession(menuService, dataStore, orderService, userService));
                chefSession.set(new ChefSession(orderService, menuService, dataStore, userService));
            }

            Object session = currentSession.get();

            if (session == null) {
                // Not logged in yet
                if (!line.toUpperCase().startsWith("LOGIN")) {
                    return "Please LOGIN first using: LOGIN <user> <pass>. Options: manager, server, chef.";
                } else {
                    // Handle login
                    if (parts.length != 3) {
                        return "ERROR usage: LOGIN <user> <pass>";
                    } else {
                        String user = parts[1].toLowerCase();
                        String pass = parts[2];

                        User us = userService.authenticate(user, pass);

                        switch (us.getRole()) {
                            case SERVER:
                                String serverResponse = serverSession.get().processCommand(line);
                                if (serverSession.get().isAuthenticated()) {
                                    currentSession.set(serverSession.get());
                                    return serverResponse;
                                }
                                return serverResponse;

                            case MANAGER:
                                String managerResponse = managerSession.get().processCommand(line);
                                if (managerSession.get().isAuthenticated()) {
                                    currentSession.set(managerSession.get());
                                    return managerResponse;
                                }
                                return managerResponse;

                            case CHEF:
                                String chefResponse = chefSession.get().processCommand(line);
                                if (chefSession.get().isAuthenticated()) {
                                    currentSession.set(chefSession.get());
                                    return "Welcome Chef! " + chefResponse;
                                }
                                return chefResponse;

                            default:
                                return "ERROR: unknown user role";
                        }
                    }
                }

            } else {
                // Already logged in - process command with current session
                String response;
                if (session == serverSession.get()) {
                    response = serverSession.get().processCommand(line);
                } else if (session == managerSession.get()) {
                    response = managerSession.get().processCommand(line);
                } else {
                    response = chefSession.get().processCommand(line);
                }

                // Handle logout
                if ("LOGOUT".equalsIgnoreCase(response)) {
                    currentSession.set(null);
                    System.out.println("Client logged out successfully.");
                    return "Logged out successfully. Please LOGIN again to continue.";
                }

                return response;
            }
        } catch (Exception e) {
            return "ERR: " + e.getMessage();
        }
    }
}