package com.restaurant.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Chef Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: restaurant_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChefServiceGrpc {

  private ChefServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.restaurant.grpc.ChefService";

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
    if ((getLoginMethod = ChefServiceGrpc.getLoginMethod) == null) {
      synchronized (ChefServiceGrpc.class) {
        if ((getLoginMethod = ChefServiceGrpc.getLoginMethod) == null) {
          ChefServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.LoginRequest, com.restaurant.grpc.generated.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChefServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
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
    if ((getListOrdersMethod = ChefServiceGrpc.getListOrdersMethod) == null) {
      synchronized (ChefServiceGrpc.class) {
        if ((getListOrdersMethod = ChefServiceGrpc.getListOrdersMethod) == null) {
          ChefServiceGrpc.getListOrdersMethod = getListOrdersMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ListOrdersRequest, com.restaurant.grpc.generated.ListOrdersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListOrdersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChefServiceMethodDescriptorSupplier("ListOrders"))
              .build();
        }
      }
    }
    return getListOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest,
      com.restaurant.grpc.generated.GenericResponse> getMarkOrderReadyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "MarkOrderReady",
      requestType = com.restaurant.grpc.generated.ShowBillRequest.class,
      responseType = com.restaurant.grpc.generated.GenericResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest,
      com.restaurant.grpc.generated.GenericResponse> getMarkOrderReadyMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.ShowBillRequest, com.restaurant.grpc.generated.GenericResponse> getMarkOrderReadyMethod;
    if ((getMarkOrderReadyMethod = ChefServiceGrpc.getMarkOrderReadyMethod) == null) {
      synchronized (ChefServiceGrpc.class) {
        if ((getMarkOrderReadyMethod = ChefServiceGrpc.getMarkOrderReadyMethod) == null) {
          ChefServiceGrpc.getMarkOrderReadyMethod = getMarkOrderReadyMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ShowBillRequest, com.restaurant.grpc.generated.GenericResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MarkOrderReady"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ShowBillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChefServiceMethodDescriptorSupplier("MarkOrderReady"))
              .build();
        }
      }
    }
    return getMarkOrderReadyMethod;
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
    if ((getLogoutMethod = ChefServiceGrpc.getLogoutMethod) == null) {
      synchronized (ChefServiceGrpc.class) {
        if ((getLogoutMethod = ChefServiceGrpc.getLogoutMethod) == null) {
          ChefServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.GenericResponse, com.restaurant.grpc.generated.GenericResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChefServiceMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChefServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChefServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChefServiceStub>() {
        @java.lang.Override
        public ChefServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChefServiceStub(channel, callOptions);
        }
      };
    return ChefServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChefServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChefServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChefServiceBlockingStub>() {
        @java.lang.Override
        public ChefServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChefServiceBlockingStub(channel, callOptions);
        }
      };
    return ChefServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChefServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChefServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChefServiceFutureStub>() {
        @java.lang.Override
        public ChefServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChefServiceFutureStub(channel, callOptions);
        }
      };
    return ChefServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Chef Service
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
    default void listOrders(com.restaurant.grpc.generated.ListOrdersRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     * ShowBillRequest reused for order_id
     * </pre>
     */
    default void markOrderReady(com.restaurant.grpc.generated.ShowBillRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMarkOrderReadyMethod(), responseObserver);
    }

    /**
     */
    default void logout(com.restaurant.grpc.generated.GenericResponse request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ChefService.
   * <pre>
   * Chef Service
   * </pre>
   */
  public static abstract class ChefServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ChefServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ChefService.
   * <pre>
   * Chef Service
   * </pre>
   */
  public static final class ChefServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ChefServiceStub> {
    private ChefServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChefServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChefServiceStub(channel, callOptions);
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
    public void listOrders(com.restaurant.grpc.generated.ListOrdersRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ShowBillRequest reused for order_id
     * </pre>
     */
    public void markOrderReady(com.restaurant.grpc.generated.ShowBillRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMarkOrderReadyMethod(), getCallOptions()), request, responseObserver);
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
   * A stub to allow clients to do synchronous rpc calls to service ChefService.
   * <pre>
   * Chef Service
   * </pre>
   */
  public static final class ChefServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ChefServiceBlockingStub> {
    private ChefServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChefServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChefServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.restaurant.grpc.generated.LoginResponse login(com.restaurant.grpc.generated.LoginRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.ListOrdersResponse listOrders(com.restaurant.grpc.generated.ListOrdersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListOrdersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ShowBillRequest reused for order_id
     * </pre>
     */
    public com.restaurant.grpc.generated.GenericResponse markOrderReady(com.restaurant.grpc.generated.ShowBillRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMarkOrderReadyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.GenericResponse logout(com.restaurant.grpc.generated.GenericResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ChefService.
   * <pre>
   * Chef Service
   * </pre>
   */
  public static final class ChefServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ChefServiceFutureStub> {
    private ChefServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChefServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChefServiceFutureStub(channel, callOptions);
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
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.ListOrdersResponse> listOrders(
        com.restaurant.grpc.generated.ListOrdersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListOrdersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ShowBillRequest reused for order_id
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.GenericResponse> markOrderReady(
        com.restaurant.grpc.generated.ShowBillRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMarkOrderReadyMethod(), getCallOptions()), request);
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
  private static final int METHODID_LIST_ORDERS = 1;
  private static final int METHODID_MARK_ORDER_READY = 2;
  private static final int METHODID_LOGOUT = 3;

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
        case METHODID_LIST_ORDERS:
          serviceImpl.listOrders((com.restaurant.grpc.generated.ListOrdersRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListOrdersResponse>) responseObserver);
          break;
        case METHODID_MARK_ORDER_READY:
          serviceImpl.markOrderReady((com.restaurant.grpc.generated.ShowBillRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse>) responseObserver);
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
          getListOrdersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.ListOrdersRequest,
              com.restaurant.grpc.generated.ListOrdersResponse>(
                service, METHODID_LIST_ORDERS)))
        .addMethod(
          getMarkOrderReadyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.ShowBillRequest,
              com.restaurant.grpc.generated.GenericResponse>(
                service, METHODID_MARK_ORDER_READY)))
        .addMethod(
          getLogoutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.GenericResponse,
              com.restaurant.grpc.generated.GenericResponse>(
                service, METHODID_LOGOUT)))
        .build();
  }

  private static abstract class ChefServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChefServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.restaurant.grpc.generated.RestaurantService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChefService");
    }
  }

  private static final class ChefServiceFileDescriptorSupplier
      extends ChefServiceBaseDescriptorSupplier {
    ChefServiceFileDescriptorSupplier() {}
  }

  private static final class ChefServiceMethodDescriptorSupplier
      extends ChefServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ChefServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ChefServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChefServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getListOrdersMethod())
              .addMethod(getMarkOrderReadyMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
