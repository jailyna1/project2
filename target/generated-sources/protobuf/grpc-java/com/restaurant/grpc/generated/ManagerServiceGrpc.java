package com.restaurant.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Manager Service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: restaurant_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ManagerServiceGrpc {

  private ManagerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.restaurant.grpc.ManagerService";

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
    if ((getLoginMethod = ManagerServiceGrpc.getLoginMethod) == null) {
      synchronized (ManagerServiceGrpc.class) {
        if ((getLoginMethod = ManagerServiceGrpc.getLoginMethod) == null) {
          ManagerServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.LoginRequest, com.restaurant.grpc.generated.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.LoginResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ManagerServiceMethodDescriptorSupplier("Login"))
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
    if ((getListMenuMethod = ManagerServiceGrpc.getListMenuMethod) == null) {
      synchronized (ManagerServiceGrpc.class) {
        if ((getListMenuMethod = ManagerServiceGrpc.getListMenuMethod) == null) {
          ManagerServiceGrpc.getListMenuMethod = getListMenuMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.ListMenuRequest, com.restaurant.grpc.generated.ListMenuResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMenu"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListMenuRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.ListMenuResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ManagerServiceMethodDescriptorSupplier("ListMenu"))
              .build();
        }
      }
    }
    return getListMenuMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.AdjustPriceRequest,
      com.restaurant.grpc.generated.AdjustPriceResponse> getAdjustPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AdjustPrice",
      requestType = com.restaurant.grpc.generated.AdjustPriceRequest.class,
      responseType = com.restaurant.grpc.generated.AdjustPriceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.AdjustPriceRequest,
      com.restaurant.grpc.generated.AdjustPriceResponse> getAdjustPriceMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.AdjustPriceRequest, com.restaurant.grpc.generated.AdjustPriceResponse> getAdjustPriceMethod;
    if ((getAdjustPriceMethod = ManagerServiceGrpc.getAdjustPriceMethod) == null) {
      synchronized (ManagerServiceGrpc.class) {
        if ((getAdjustPriceMethod = ManagerServiceGrpc.getAdjustPriceMethod) == null) {
          ManagerServiceGrpc.getAdjustPriceMethod = getAdjustPriceMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.AdjustPriceRequest, com.restaurant.grpc.generated.AdjustPriceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AdjustPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.AdjustPriceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.AdjustPriceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ManagerServiceMethodDescriptorSupplier("AdjustPrice"))
              .build();
        }
      }
    }
    return getAdjustPriceMethod;
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
    if ((getLogoutMethod = ManagerServiceGrpc.getLogoutMethod) == null) {
      synchronized (ManagerServiceGrpc.class) {
        if ((getLogoutMethod = ManagerServiceGrpc.getLogoutMethod) == null) {
          ManagerServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.GenericResponse, com.restaurant.grpc.generated.GenericResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.GenericResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ManagerServiceMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ManagerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ManagerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ManagerServiceStub>() {
        @java.lang.Override
        public ManagerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ManagerServiceStub(channel, callOptions);
        }
      };
    return ManagerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ManagerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ManagerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ManagerServiceBlockingStub>() {
        @java.lang.Override
        public ManagerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ManagerServiceBlockingStub(channel, callOptions);
        }
      };
    return ManagerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ManagerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ManagerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ManagerServiceFutureStub>() {
        @java.lang.Override
        public ManagerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ManagerServiceFutureStub(channel, callOptions);
        }
      };
    return ManagerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Manager Service
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
    default void adjustPrice(com.restaurant.grpc.generated.AdjustPriceRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.AdjustPriceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAdjustPriceMethod(), responseObserver);
    }

    /**
     */
    default void logout(com.restaurant.grpc.generated.GenericResponse request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.GenericResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ManagerService.
   * <pre>
   * Manager Service
   * </pre>
   */
  public static abstract class ManagerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ManagerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ManagerService.
   * <pre>
   * Manager Service
   * </pre>
   */
  public static final class ManagerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ManagerServiceStub> {
    private ManagerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ManagerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ManagerServiceStub(channel, callOptions);
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
    public void adjustPrice(com.restaurant.grpc.generated.AdjustPriceRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.AdjustPriceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAdjustPriceMethod(), getCallOptions()), request, responseObserver);
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
   * A stub to allow clients to do synchronous rpc calls to service ManagerService.
   * <pre>
   * Manager Service
   * </pre>
   */
  public static final class ManagerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ManagerServiceBlockingStub> {
    private ManagerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ManagerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ManagerServiceBlockingStub(channel, callOptions);
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
    public com.restaurant.grpc.generated.AdjustPriceResponse adjustPrice(com.restaurant.grpc.generated.AdjustPriceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAdjustPriceMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.restaurant.grpc.generated.GenericResponse logout(com.restaurant.grpc.generated.GenericResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ManagerService.
   * <pre>
   * Manager Service
   * </pre>
   */
  public static final class ManagerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ManagerServiceFutureStub> {
    private ManagerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ManagerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ManagerServiceFutureStub(channel, callOptions);
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
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.AdjustPriceResponse> adjustPrice(
        com.restaurant.grpc.generated.AdjustPriceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAdjustPriceMethod(), getCallOptions()), request);
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
  private static final int METHODID_ADJUST_PRICE = 2;
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
        case METHODID_LIST_MENU:
          serviceImpl.listMenu((com.restaurant.grpc.generated.ListMenuRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.ListMenuResponse>) responseObserver);
          break;
        case METHODID_ADJUST_PRICE:
          serviceImpl.adjustPrice((com.restaurant.grpc.generated.AdjustPriceRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.AdjustPriceResponse>) responseObserver);
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
          getAdjustPriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.AdjustPriceRequest,
              com.restaurant.grpc.generated.AdjustPriceResponse>(
                service, METHODID_ADJUST_PRICE)))
        .addMethod(
          getLogoutMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.GenericResponse,
              com.restaurant.grpc.generated.GenericResponse>(
                service, METHODID_LOGOUT)))
        .build();
  }

  private static abstract class ManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ManagerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.restaurant.grpc.generated.RestaurantService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ManagerService");
    }
  }

  private static final class ManagerServiceFileDescriptorSupplier
      extends ManagerServiceBaseDescriptorSupplier {
    ManagerServiceFileDescriptorSupplier() {}
  }

  private static final class ManagerServiceMethodDescriptorSupplier
      extends ManagerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ManagerServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ManagerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ManagerServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getListMenuMethod())
              .addMethod(getAdjustPriceMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
