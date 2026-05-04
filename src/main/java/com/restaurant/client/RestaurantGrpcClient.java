package com.restaurant.client;

import com.restaurant.grpc.generated.CommandServiceGrpc;
import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class RestaurantGrpcClient {
    private final ManagedChannel channel;
    private final CommandServiceGrpc.CommandServiceBlockingStub stub;

    public RestaurantGrpcClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = CommandServiceGrpc.newBlockingStub(channel);
    }

    public void start() {
        System.out.println("WELCOME TO RESTAURANT X. Please LOGIN with: LOGIN <user> <pass>.");

        Scanner scanner = new Scanner(System.in);
        String line;

        while (true) {
            System.out.print("> ");
            line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("EXIT") || line.equalsIgnoreCase("QUIT")) {
                System.exit(0);
                break;
            }

            if (!line.isEmpty()) {
                try {
                    CommandRequest request = CommandRequest.newBuilder()
                            .setCommand(line)
                            .build();

                    CommandResponse response = stub.sendCommand(request);
                    System.out.println(response.getResponse());

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }

        shutdown();
    }

    public void shutdown() {
        channel.shutdown();
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }

        RestaurantGrpcClient client = new RestaurantGrpcClient(host, port);
        client.start();
    }
}

