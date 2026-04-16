package com.restaurant.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Unified Command Service (ClientHandler-style)
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: restaurant_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CommandServiceGrpc {

  private CommandServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.restaurant.grpc.CommandService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.restaurant.grpc.generated.CommandRequest,
      com.restaurant.grpc.generated.CommandResponse> getSendCommandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendCommand",
      requestType = com.restaurant.grpc.generated.CommandRequest.class,
      responseType = com.restaurant.grpc.generated.CommandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.restaurant.grpc.generated.CommandRequest,
      com.restaurant.grpc.generated.CommandResponse> getSendCommandMethod() {
    io.grpc.MethodDescriptor<com.restaurant.grpc.generated.CommandRequest, com.restaurant.grpc.generated.CommandResponse> getSendCommandMethod;
    if ((getSendCommandMethod = CommandServiceGrpc.getSendCommandMethod) == null) {
      synchronized (CommandServiceGrpc.class) {
        if ((getSendCommandMethod = CommandServiceGrpc.getSendCommandMethod) == null) {
          CommandServiceGrpc.getSendCommandMethod = getSendCommandMethod =
              io.grpc.MethodDescriptor.<com.restaurant.grpc.generated.CommandRequest, com.restaurant.grpc.generated.CommandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendCommand"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.CommandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.restaurant.grpc.generated.CommandResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CommandServiceMethodDescriptorSupplier("SendCommand"))
              .build();
        }
      }
    }
    return getSendCommandMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CommandServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommandServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommandServiceStub>() {
        @java.lang.Override
        public CommandServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommandServiceStub(channel, callOptions);
        }
      };
    return CommandServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CommandServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommandServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommandServiceBlockingStub>() {
        @java.lang.Override
        public CommandServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommandServiceBlockingStub(channel, callOptions);
        }
      };
    return CommandServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CommandServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommandServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommandServiceFutureStub>() {
        @java.lang.Override
        public CommandServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommandServiceFutureStub(channel, callOptions);
        }
      };
    return CommandServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * Unified Command Service (ClientHandler-style)
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void sendCommand(com.restaurant.grpc.generated.CommandRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.CommandResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendCommandMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CommandService.
   * <pre>
   * Unified Command Service (ClientHandler-style)
   * </pre>
   */
  public static abstract class CommandServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CommandServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CommandService.
   * <pre>
   * Unified Command Service (ClientHandler-style)
   * </pre>
   */
  public static final class CommandServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CommandServiceStub> {
    private CommandServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommandServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommandServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendCommand(com.restaurant.grpc.generated.CommandRequest request,
        io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.CommandResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendCommandMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CommandService.
   * <pre>
   * Unified Command Service (ClientHandler-style)
   * </pre>
   */
  public static final class CommandServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CommandServiceBlockingStub> {
    private CommandServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommandServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommandServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.restaurant.grpc.generated.CommandResponse sendCommand(com.restaurant.grpc.generated.CommandRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendCommandMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CommandService.
   * <pre>
   * Unified Command Service (ClientHandler-style)
   * </pre>
   */
  public static final class CommandServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CommandServiceFutureStub> {
    private CommandServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommandServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommandServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.restaurant.grpc.generated.CommandResponse> sendCommand(
        com.restaurant.grpc.generated.CommandRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendCommandMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_COMMAND = 0;

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
        case METHODID_SEND_COMMAND:
          serviceImpl.sendCommand((com.restaurant.grpc.generated.CommandRequest) request,
              (io.grpc.stub.StreamObserver<com.restaurant.grpc.generated.CommandResponse>) responseObserver);
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
          getSendCommandMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.restaurant.grpc.generated.CommandRequest,
              com.restaurant.grpc.generated.CommandResponse>(
                service, METHODID_SEND_COMMAND)))
        .build();
  }

  private static abstract class CommandServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CommandServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.restaurant.grpc.generated.RestaurantService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CommandService");
    }
  }

  private static final class CommandServiceFileDescriptorSupplier
      extends CommandServiceBaseDescriptorSupplier {
    CommandServiceFileDescriptorSupplier() {}
  }

  private static final class CommandServiceMethodDescriptorSupplier
      extends CommandServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CommandServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CommandServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CommandServiceFileDescriptorSupplier())
              .addMethod(getSendCommandMethod())
              .build();
        }
      }
    }
    return result;
  }
}
