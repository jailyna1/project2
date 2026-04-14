package com.restaurant.server;

import io.grpc.stub.StreamObserver;
import com.restaurant.grpc.generated.*;
import com.restaurant.model.Order;
import com.restaurant.model.OrderLine;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.DataStore;

import java.util.List;

public class ChefGrpcImpl extends ChefServiceGrpc.ChefServiceImplBase {
    private final OrderService orderService;
    private final MenuService menuService;
    private final DataStore dataStore;
    private boolean authenticated = false;

    public ChefGrpcImpl(OrderService orderService, MenuService menuService, DataStore dataStore) {
        this.orderService = orderService;
        this.menuService = menuService;
        this.dataStore = dataStore;
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        boolean auth = authenticate(request.getUser(), request.getPass());
        LoginResponse response = LoginResponse.newBuilder()
                .setAuthenticated(auth)
                .setMessage(auth ? "Logged in as CHEF" : "Error invalid credentials")
                .build();

        if (auth) {
            authenticated = true;
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listOrders(ListOrdersRequest request, StreamObserver<ListOrdersResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        List<Order> orders = orderService.listOrders();
        ListOrdersResponse.Builder builder = ListOrdersResponse.newBuilder();

        for (Order order : orders) {
            OrderMessage.Builder orderBuilder = OrderMessage.newBuilder()
                    .setId(order.getId())
                    .setCustomerName(order.getCustomerName())
                    .setOrderType(order.getOrderType())
                    .setStatus(mapOrderStatus(order.getStatus()));

            for (OrderLine line : order.getLines()) {
                OrderLineMessage lineMsg = OrderLineMessage.newBuilder()
                        .setItem(MenuItemMessage.newBuilder()
                                .setName(line.getItem().getName())
                                .setCategory(line.getItem().getCategory())
                                .setPrice(line.getItem().getPrice())
                                .build())
                        .setQuantity(line.getQty())
                        .build();
                orderBuilder.addLines(lineMsg);
            }

            builder.addOrders(orderBuilder.build());
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void markOrderReady(ShowBillRequest request, StreamObserver<GenericResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        Order order = orderService.getOrder(request.getOrderId());
        if (order == null) {
            GenericResponse response = GenericResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: Order not found")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        order.setStatus("READY");
        dataStore.saveOrders();

        GenericResponse response = GenericResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Order #" + request.getOrderId() + " marked as READY")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void logout(GenericResponse request, StreamObserver<GenericResponse> responseObserver) {
        authenticated = false;
        GenericResponse response = GenericResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Logged out!")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private boolean authenticate(String user, String pass) {
        return ("chef".equalsIgnoreCase(user) && "pass".equals(pass));
    }

    private OrderStatus mapOrderStatus(String status) {
        switch (status.toUpperCase()) {
            case "READY":
                return OrderStatus.READY;
            case "COMPLETED":
                return OrderStatus.COMPLETED;
            default:
                return OrderStatus.PENDING;
        }
    }
}
