package com.restaurant.server;

import io.grpc.stub.StreamObserver;
import com.restaurant.grpc.generated.*;
import com.restaurant.model.MenuItem;
import com.restaurant.service.MenuService;
import com.restaurant.service.OrderService;
import com.restaurant.service.DataStore;

import java.util.List;

public class ManagerGrpcImpl extends ManagerServiceGrpc.ManagerServiceImplBase {
    private final MenuService menuService;
    private final DataStore dataStore;
    private final OrderService orderService;
    private boolean authenticated = false;

    public ManagerGrpcImpl(MenuService menuService, DataStore dataStore, OrderService os) {
        this.menuService = menuService;
        this.dataStore = dataStore;
        this.orderService = os;
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        boolean auth = authenticate(request.getUser(), request.getPass());
        LoginResponse response = LoginResponse.newBuilder()
                .setAuthenticated(auth)
                .setMessage(auth ? "Logged in as MANAGER" : "Error invalid credentials")
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

    //modify this to list employees instead of menu items
    // @Override
    // public void listEmployees(ListMenuRequest request, StreamObserver<ListMenuResponse> responseObserver) {
    //     if (!authenticated) {
    //         responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
    //         return;
    //     }

    //     List<MenuItem> items = menuService.listAll();
    //     ListMenuResponse.Builder builder = ListMenuResponse.newBuilder();

    //     for (MenuItem item : items) {
    //         MenuItemMessage msg = MenuItemMessage.newBuilder()
    //                 .setName(item.getName())
    //                 .setCategory(item.getCategory())
    //                 .setPrice(item.getPrice())
    //                 .build();
    //         builder.addItems(msg);
    //     }

    //     responseObserver.onNext(builder.build());
    //     responseObserver.onCompleted();
    // }

    @Override
    public void adjustPrice(AdjustPriceRequest request, StreamObserver<AdjustPriceResponse> responseObserver) {
        if (!authenticated) {
            responseObserver.onError(new RuntimeException("Not authenticated. Use LOGIN first."));
            return;
        }

        boolean success = menuService.adjustPrice(request.getItemName(), request.getNewPrice());
        AdjustPriceResponse response = AdjustPriceResponse.newBuilder()
                .setSuccess(success)
                .setMessage(success ? "Price updated" : "Error item not found")
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
        return ("manager".equalsIgnoreCase(user) && "pass".equals(pass));
    }
}
