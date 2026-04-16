package com.restaurant.server;

import com.restaurant.grpc.generated.CommandServiceGrpc;
import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;
import com.restaurant.service.DataStore;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import io.grpc.stub.StreamObserver;

public class CommandGrpcImpl extends CommandServiceGrpc.CommandServiceImplBase {

    private final DataStore dataStore;
    private final MenuService menuService;
    private final OrderService orderService;

    // Session management per client (simplified - in production you'd use proper session management)
    private final ThreadLocal<ServerSession> serverSession = new ThreadLocal<>();
    private final ThreadLocal<ManagerSession> managerSession = new ThreadLocal<>();
    private final ThreadLocal<ChefSession> chefSession = new ThreadLocal<>();
    private final ThreadLocal<Object> currentSession = new ThreadLocal<>();

    public CommandGrpcImpl(MenuService menuService, DataStore dataStore, OrderService orderService) {
        this.menuService = menuService;
        this.dataStore = dataStore;
        this.orderService = orderService;
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
                serverSession.set(new ServerSession(menuService, orderService, dataStore));
                managerSession.set(new ManagerSession(menuService, dataStore, orderService));
                chefSession.set(new ChefSession(orderService, menuService, dataStore));
            }

            Object session = currentSession.get();

            if (session == null) {
                // Not logged in yet
                if (!line.toUpperCase().startsWith("LOGIN")) {
                    return "Please LOGIN first using: LOGIN <user> <pass>";
                } else {
                    // Handle login
                    if (parts.length < 3) {
                        return "ERR usage: LOGIN <user> <pass>";
                    } else {
                        String user = parts[1].toLowerCase();
                        String pass = parts[2];

                        switch (user) {
                            case "server":
                                String serverResponse = serverSession.get().processCommand(line);
                                if (serverSession.get().isAuthenticated()) {
                                    currentSession.set(serverSession.get());
                                    return serverResponse;
                                }
                                return serverResponse;

                            case "manager":
                                String managerResponse = managerSession.get().processCommand(line);
                                if (managerSession.get().isAuthenticated()) {
                                    currentSession.set(managerSession.get());
                                    return managerResponse;
                                }
                                return managerResponse;

                            case "chef":
                                String chefResponse = chefSession.get().processCommand(line);
                                if (chefSession.get().isAuthenticated()) {
                                    currentSession.set(chefSession.get());
                                    return "Welcome Chef! " + chefResponse;
                                }
                                return chefResponse;

                            default:
                                return "ERR unknown user role";
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
                    return "Logged out successfully";
                }

                return response;
            }
        } catch (Exception e) {
            return "ERR: " + e.getMessage();
        }
    }
}