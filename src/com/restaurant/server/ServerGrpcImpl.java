package com.restaurant.server;

import io.grpc.stub.StreamObserver;
import com.restaurant.grpc.generated.*;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderLine;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.DataStore;

import java.util.ArrayList;
import java.util.List;

public class ServerGrpcImpl extends ServerServiceGrpc.ServerServiceImplBase {
    private final MenuService menuService;
    private final OrderService orderService;
    private final DataStore dataStore;
    private boolean authenticated = false;

    public ServerGrpcImpl(MenuService menuService, OrderService orderService, DataStore dataStore) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.dataStore = dataStore;
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        boolean auth = authenticate(request.getUser(), request.getPass());
        LoginResponse response = LoginResponse.newBuilder()
                .setAuthenticated(auth)
                .setMessage(auth ? "Logged in as SERVER" : "Error invalid credentials")
                .build();

        if (auth) {
            authenticated = true;
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listMenu(ListMenuRequest request, StreamObserver<ListMenuResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        List<MenuItem> items = menuService.listAll();
        ListMenuResponse.Builder builder = ListMenuResponse.newBuilder();

        for (MenuItem item : items) {
            MenuItemMessage msg = MenuItemMessage.newBuilder()
                    .setName(item.getName())
                    .setCategory(item.getCategory())
                    .setPrice(item.getPrice())
                    .build();
            builder.addItems(msg);
        }

        responseObserver.onNext(builder.build());
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
    public void placeTakeoutOrder(PlaceTakeoutOrderRequest request, StreamObserver<PlaceTakeoutOrderResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        if (request.getCustomerName().isEmpty() || request.getItemNamesList().isEmpty()) {
            PlaceTakeoutOrderResponse response = PlaceTakeoutOrderResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error usage: PLACE_TAKEOUT_ORDER <customerName> <item1> <item2> ... (up to 10 items)")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        Order newOrder = orderService.createTakeOutOrder(request.getCustomerName());

        for (String itemName : request.getItemNamesList()) {
            MenuItem menuItem = menuService.findByName(itemName);
            if (menuItem == null) {
                PlaceTakeoutOrderResponse response = PlaceTakeoutOrderResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Error: Menu item '" + itemName + "' not found")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }
            orderService.addOrderLine(newOrder.getId(), itemName);
        }

        PlaceTakeoutOrderResponse response = PlaceTakeoutOrderResponse.newBuilder()
                .setSuccess(true)
                .setOrderId(newOrder.getId())
                .setMessage("Takeout order completed with ID #" + newOrder.getId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void placeDineinOrder(PlaceDineinOrderRequest request, StreamObserver<PlaceDineinOrderResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        if (request.getGuestsList().isEmpty()) {
            PlaceDineinOrderResponse response = PlaceDineinOrderResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: At least one guest is required")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            return;
        }

        List<Integer> orderIds = new ArrayList<>();

        for (GuestOrder guest : request.getGuestsList()) {
            Order order = orderService.createDineInOrder(guest.getCustomerName());
            orderIds.add(order.getId());

            for (String itemName : guest.getItemNamesList()) {
                MenuItem menuItem = menuService.findByName(itemName);
                if (menuItem == null) {
                    PlaceDineinOrderResponse response = PlaceDineinOrderResponse.newBuilder()
                            .setSuccess(false)
                            .setMessage("Error: Menu item '" + itemName + "' not found")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                    return;
                }
                orderService.addOrderLine(order.getId(), itemName);
            }
        }

        PlaceDineinOrderResponse response = PlaceDineinOrderResponse.newBuilder()
                .setSuccess(true)
                .addAllOrderIds(orderIds)
                .setMessage("Dine-in orders completed with IDs: " + orderIds)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void showBill(ShowBillRequest request, StreamObserver<ShowBillResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        String bill = orderService.showBill(request.getOrderId());

        ShowBillResponse response = ShowBillResponse.newBuilder()
                .setSuccess(true)
                .setBill(bill)
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
        return ("server".equalsIgnoreCase(user) && "pass".equals(pass));
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
