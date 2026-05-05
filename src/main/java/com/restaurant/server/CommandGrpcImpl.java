package com.restaurant.server;

import com.restaurant.grpc.generated.CommandServiceGrpc;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;
import com.restaurant.service.DataStore;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.UserService;
import io.grpc.stub.StreamObserver;

public class CommandGrpcImpl extends CommandServiceGrpc.CommandServiceImplBase {

    private final RestaurantGrpcServer server;
    private final DataStore dataStore;
    private final MenuService menuService;
    private final OrderService orderService;
    private final UserService userService;

    private final ConcurrentHashMap<String, RestaurantSession> sessions = new ConcurrentHashMap<>();

    public CommandGrpcImpl(RestaurantGrpcServer server, DataStore dataStore, MenuService menuService, OrderService orderService, UserService userService) {
        this.server = server;
        this.menuService = menuService;
        this.dataStore = dataStore;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public void sendCommand(CommandRequest request, StreamObserver<CommandResponse> responseObserver) {
        try {
            String command = request.getCommand();
            String sessionId = request.getSessionId();
            if (command != null) {
                command = command.trim();
            }
            if (sessionId != null) {
                sessionId = sessionId.trim();
            }

            CommandResponse.Builder responseBuilder = CommandResponse.newBuilder();
            String response = processCommand(command, sessionId, responseBuilder);

            responseBuilder.setResponse(response);
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private String processCommand(String line, String sessionId, CommandResponse.Builder responseBuilder) {
        try {
            if (line == null || line.trim().isEmpty()) {
                return "";
            }

            line = line.trim();
            String[] parts = line.split("\\s+");
            String cmd = parts[0].toUpperCase();

            RestaurantSession session = null;

            if (sessionId != null && !sessionId.isEmpty()) {
                session = sessions.get(sessionId);
            }

            if (session == null) {
                if (!cmd.equals("LOGIN")) {
                    return "Please LOGIN first using: LOGIN <user> <pass>";
                }
                session = new RestaurantSession(server, dataStore, menuService, orderService, userService);
            }

            String response = session.processCommand(line);

            if (cmd.equals("LOGIN") && session.isAuthenticated()) {
                if (sessionId == null || sessionId.isEmpty() || !sessions.containsKey(sessionId)) {
                    sessionId = UUID.randomUUID().toString();
                }
                sessions.put(sessionId, session);
            }

            if (cmd.equals("LOGOUT") || cmd.equals("EXIT")) {
                if (sessionId != null && !sessionId.isEmpty()) {
                    sessions.remove(sessionId);
                }
            }

            if (sessionId != null && !sessionId.isEmpty()) {
                responseBuilder.setSessionId(sessionId);
            }

            return response;
        } catch (Exception e) {
            return "ERR: " + e.getMessage();
        }
    }
}