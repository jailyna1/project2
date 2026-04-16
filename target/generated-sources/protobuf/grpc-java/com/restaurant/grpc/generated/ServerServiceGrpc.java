package com.restaurant.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Server/Client Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: restaurant_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServerServiceGrpc {

  private ServerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.restaurant.grpc.ServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.LoginRequest,
      com.restaurant.grpc.generated.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.restaurant.grpc.generated.LoginRequest.class,
      responseType = com.restaurant.grpc.generated.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.LoginRequest,
      com.restaurant.grpc.generated.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.LoginRequest, com.restaurant.grpc.generated.LoginResponse> getLoginMethod;
    if ((getLoginMethod = ServerServiceGrpc.getLoginMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getLoginMethod = ServerServiceGrpc.getLoginMethod) == null) {
          ServerServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.LoginRequest, com.restaurant.grpc.generated.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListMenuRequest,
      com.restaurant.grpc.generated.ListMenuResponse> getListMenuMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMenu",
      requestType = com.restaurant.grpc.generated.ListMenuRequest.class,
      responseType = com.restaurant.grpc.generated.ListMenuResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListMenuRequest,
      com.restaurant.grpc.generated.ListMenuResponse> getListMenuMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListMenuRequest, com.restaurant.grpc.generated.ListMenuResponse> getListMenuMethod;
    if ((getListMenuMethod = ServerServiceGrpc.getListMenuMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getListMenuMethod = ServerServiceGrpc.getListMenuMethod) == null) {
          ServerServiceGrpc.getListMenuMethod = getListMenuMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ListMenuRequest, com.restaurant.grpc.generated.ListMenuResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMenu"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListMenuRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListMenuResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("ListMenu"))
              .build();
        }
      }
    }
    return getListMenuMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListOrdersRequest,
      com.restaurant.grpc.generated.ListOrdersResponse> getListOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListOrders",
      requestType = com.restaurant.grpc.generated.ListOrdersRequest.class,
      responseType = com.restaurant.grpc.generated.ListOrdersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListOrdersRequest,
      com.restaurant.grpc.generated.ListOrdersResponse> getListOrdersMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ListOrdersRequest, com.restaurant.grpc.generated.ListOrdersResponse> getListOrdersMethod;
    if ((getListOrdersMethod = ServerServiceGrpc.getListOrdersMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getListOrdersMethod = ServerServiceGrpc.getListOrdersMethod) == null) {
          ServerServiceGrpc.getListOrdersMethod = getListOrdersMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ListOrdersRequest, com.restaurant.grpc.generated.ListOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("ListOrders"))
              .build();
        }
      }
    }
    return getListOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceTakeoutOrderRequest,
      com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> getPlaceTakeoutOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PlaceTakeoutOrder",
      requestType = com.restaurant.grpc.generated.PlaceTakeoutOrderRequest.class,
      responseType = com.restaurant.grpc.generated.PlaceTakeoutOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceTakeoutOrderRequest,
      com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> getPlaceTakeoutOrderMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceTakeoutOrderRequest, com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> getPlaceTakeoutOrderMethod;
    if ((getPlaceTakeoutOrderMethod = ServerServiceGrpc.getPlaceTakeoutOrderMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getPlaceTakeoutOrderMethod = ServerServiceGrpc.getPlaceTakeoutOrderMethod) == null) {
          ServerServiceGrpc.getPlaceTakeoutOrderMethod = getPlaceTakeoutOrderMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.PlaceTakeoutOrderRequest, com.restaurant.grpc.generated.PlaceTakeoutOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PlaceTakeoutOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.PlaceTakeoutOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.PlaceTakeoutOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("PlaceTakeoutOrder"))
              .build();
        }
      }
    }
    return getPlaceTakeoutOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceDineinOrderRequest,
      com.restaurant.grpc.generated.PlaceDineinOrderResponse> getPlaceDineinOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PlaceDineinOrder",
      requestType = com.restaurant.grpc.generated.PlaceDineinOrderRequest.class,
      responseType = com.restaurant.grpc.generated.PlaceDineinOrderResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceDineinOrderRequest,
      com.restaurant.grpc.generated.PlaceDineinOrderResponse> getPlaceDineinOrderMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.PlaceDineinOrderRequest, com.restaurant.grpc.generated.PlaceDineinOrderResponse> getPlaceDineinOrderMethod;
    if ((getPlaceDineinOrderMethod = ServerServiceGrpc.getPlaceDineinOrderMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getPlaceDineinOrderMethod = ServerServiceGrpc.getPlaceDineinOrderMethod) == null) {
          ServerServiceGrpc.getPlaceDineinOrderMethod = getPlaceDineinOrderMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.PlaceDineinOrderRequest, com.restaurant.grpc.generated.PlaceDineinOrderResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PlaceDineinOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.PlaceDineinOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.PlaceDineinOrderResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("PlaceDineinOrder"))
              .build();
        }
      }
    }
    return getPlaceDineinOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest,
      com.restaurant.grpc.generated.ShowBillResponse> getShowBillMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShowBill",
      requestType = com.restaurant.grpc.generated.ShowBillRequest.class,
      responseType = com.restaurant.grpc.generated.ShowBillResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest,
      com.restaurant.grpc.generated.ShowBillResponse> getShowBillMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest, com.restaurant.grpc.generated.ShowBillResponse> getShowBillMethod;
    if ((getShowBillMethod = ServerServiceGrpc.getShowBillMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getShowBillMethod = ServerServiceGrpc.getShowBillMethod) == null) {
          ServerServiceGrpc.getShowBillMethod = getShowBillMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ShowBillRequest, com.restaurant.grpc.generated.ShowBillResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShowBill"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ShowBillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ShowBillResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("ShowBill"))
              .build();
        }
      }
    }
    return getShowBillMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.GenericResponse,
      com.restaurant.grpc.generated.GenericResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Logout",
      requestType = com.restaurant.grpc.generated.GenericResponse.class,
      responseType = com.restaurant.grpc.generated.GenericResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.GenericResponse,
      com.restaurant.grpc.generated.GenericResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.GenericResponse, com.restaurant.grpc.generated.GenericResponse> getLogoutMethod;
    if ((getLogoutMethod = ServerServiceGrpc.getLogoutMethod) == null) {
      synchronized (ServerServiceGrpc.class) {
        if ((getLogoutMethod = ServerServiceGrpc.getLogoutMethod) == null) {
          ServerServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.GenericResponse, com.restaurant.grpc.generated.GenericResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ServerServiceMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerServiceStub>() {
        @java.lang.Override
        public ServerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerServiceStub(channel, callOptions);
        }
      };
    return ServerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerServiceBlockingStub>() {
        @java.lang.Override
        public ServerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerServiceBlockingStub(channel, callOptions);
        }
      };
    return ServerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerServiceFutureStub>() {
        @java.lang.Override
        public ServerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerServiceFutureStub(channel, callOptions);
        }
      };
    return ServerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Server/Client Service
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void login(com.restaurant.grpc.generated.LoginRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.LoginResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    default void listMenu(com.restaurant.grpc.generated.ListMenuRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListMenuResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMenuMethod(), responseObserver);
    }

    /**
     */
    default void listOrders(com.restaurant.grpc.generated.ListOrdersRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListOrdersMethod(), responseObserver);
    }

    /**
     */
    default void placeTakeoutOrder(com.restaurant.grpc.generated.PlaceTakeoutOrderRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPlaceTakeoutOrderMethod(), responseObserver);
    }

    /**
     */
    default void placeDineinOrder(com.restaurant.grpc.generated.PlaceDineinOrderRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceDineinOrderResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPlaceDineinOrderMethod(), responseObserver);
    }

    /**
     */
    default void showBill(com.restaurant.grpc.generated.ShowBillRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ShowBillResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getShowBillMethod(), responseObserver);
    }

    /**
     */
    default void logout(com.restaurant.grpc.generated.GenericResponse request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ServerService.
   * <pre>
   * Server/Client Service
   * </pre>
   */
  public static abstract class ServerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ServerService.
   * <pre>
   * Server/Client Service
   * </pre>
   */
  public static final class ServerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ServerServiceStub> {
    private ServerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerServiceStub(channel, callOptions);
    }

    /**
     */
    public void login(com.restaurant.grpc.generated.LoginRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.LoginResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listMenu(com.restaurant.grpc.generated.ListMenuRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListMenuResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMenuMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listOrders(com.restaurant.grpc.generated.ListOrdersRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void placeTakeoutOrder(com.restaurant.grpc.generated.PlaceTakeoutOrderRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPlaceTakeoutOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void placeDineinOrder(com.restaurant.grpc.generated.PlaceDineinOrderRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceDineinOrderResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPlaceDineinOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void showBill(com.restaurant.grpc.generated.ShowBillRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ShowBillResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getShowBillMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(com.restaurant.grpc.generated.GenericResponse request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ServerService.
   * <pre>
   * Server/Client Service
   * </pre>
   */
  public static final class ServerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServerServiceBlockingStub> {
    private ServerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.restaurant.grpc.generated.LoginResponse login(com.restaurant.grpc.generated.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.ListMenuResponse listMenu(com.restaurant.grpc.generated.ListMenuRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMenuMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.ListOrdersResponse listOrders(com.restaurant.grpc.generated.ListOrdersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.PlaceTakeoutOrderResponse placeTakeoutOrder(com.restaurant.grpc.generated.PlaceTakeoutOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPlaceTakeoutOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.PlaceDineinOrderResponse placeDineinOrder(com.restaurant.grpc.generated.PlaceDineinOrderRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPlaceDineinOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.ShowBillResponse showBill(com.restaurant.grpc.generated.ShowBillRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getShowBillMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.GenericResponse logout(com.restaurant.grpc.generated.GenericResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ServerService.
   * <pre>
   * Server/Client Service
   * </pre>
   */
  public static final class ServerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServerServiceFutureStub> {
    private ServerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.LoginResponse> login(
        com.restaurant.grpc.generated.LoginRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.ListMenuResponse> listMenu(
        com.restaurant.grpc.generated.ListMenuRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMenuMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.ListOrdersResponse> listOrders(
        com.restaurant.grpc.generated.ListOrdersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.PlaceTakeoutOrderResponse> placeTakeoutOrder(
        com.restaurant.grpc.generated.PlaceTakeoutOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPlaceTakeoutOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.PlaceDineinOrderResponse> placeDineinOrder(
        com.restaurant.grpc.generated.PlaceDineinOrderRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPlaceDineinOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.ShowBillResponse> showBill(
        com.restaurant.grpc.generated.ShowBillRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getShowBillMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.GenericResponse> logout(
        com.restaurant.grpc.generated.GenericResponse request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_LIST_MENU = 1;
  private static final int METHODID_LIST_ORDERS = 2;
  private static final int METHODID_PLACE_TAKEOUT_ORDER = 3;
  private static final int METHODID_PLACE_DINEIN_ORDER = 4;
  private static final int METHODID_SHOW_BILL = 5;
  private static final int METHODID_LOGOUT = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((com.restaurant.grpc.generated.LoginRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.LoginResponse>) responseObserver);
          break;
        case METHODID_LIST_MENU:
          serviceImpl.listMenu((com.restaurant.grpc.generated.ListMenuRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListMenuResponse>) responseObserver);
          break;
        case METHODID_LIST_ORDERS:
          serviceImpl.listOrders((com.restaurant.grpc.generated.ListOrdersRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse>) responseObserver);
          break;
        case METHODID_PLACE_TAKEOUT_ORDER:
          serviceImpl.placeTakeoutOrder((com.restaurant.grpc.generated.PlaceTakeoutOrderRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceTakeoutOrderResponse>) responseObserver);
          break;
        case METHODID_PLACE_DINEIN_ORDER:
          serviceImpl.placeDineinOrder((com.restaurant.grpc.generated.PlaceDineinOrderRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.PlaceDineinOrderResponse>) responseObserver);
          break;
        case METHODID_SHOW_BILL:
          serviceImpl.showBill((com.restaurant.grpc.generated.ShowBillRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ShowBillResponse>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.restaurant.grpc.generated.GenericResponse) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getLoginMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.LoginRequest,
              com.restaurant.grpc.generated.LoginResponse>(
                service, METHODID_LOGIN)))
        .addMethod(
          getListMenuMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.ListMenuRequest,
              com.restaurant.grpc.generated.ListMenuResponse>(
                service, METHODID_LIST_MENU)))
        .addMethod(
          getListOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.ListOrdersRequest,
              com.restaurant.grpc.generated.ListOrdersResponse>(
                service, METHODID_LIST_ORDERS)))
        .addMethod(
          getPlaceTakeoutOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.PlaceTakeoutOrderRequest,
              com.restaurant.grpc.generated.PlaceTakeoutOrderResponse>(
                service, METHODID_PLACE_TAKEOUT_ORDER)))
        .addMethod(
          getPlaceDineinOrderMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.PlaceDineinOrderRequest,
              com.restaurant.grpc.generated.PlaceDineinOrderResponse>(
                service, METHODID_PLACE_DINEIN_ORDER)))
        .addMethod(
          getShowBillMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.ShowBillRequest,
              com.restaurant.grpc.generated.ShowBillResponse>(
                service, METHODID_SHOW_BILL)))
        .addMethod(
          getLogoutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.GenericResponse,
              com.restaurant.grpc.generated.GenericResponse>(
                service, METHODID_LOGOUT)))
        .build();
  }

  private static abstract class ServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.restaurant.grpc.generated.RestaurantService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ServerService");
    }
  }

  private static final class ServerServiceFileDescriptorSupplier
      extends ServerServiceBaseDescriptorSupplier {
    ServerServiceFileDescriptorSupplier() {}
  }

  private static final class ServerServiceMethodDescriptorSupplier
      extends ServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ServerServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServerServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getListMenuMethod())
              .addMethod(getListOrdersMethod())
              .addMethod(getPlaceTakeoutOrderMethod())
              .addMethod(getPlaceDineinOrderMethod())
              .addMethod(getShowBillMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
