import javax.swing.*;
import java.awt.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.restaurant.grpc.generated.CommandServiceGrpc;
import com.restaurant.grpc.generated.CommandRequest;
import com.restaurant.grpc.generated.CommandResponse;

public class RestaurantWeb {

    private static String sessionId = "";
    private static CommandServiceGrpc.CommandServiceBlockingStub stub;
    private static JTextArea outputArea;
    private static JTextField inputField;
    private static JFrame frame;

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5000)
                .usePlaintext()
                .build();

        stub = CommandServiceGrpc.newBlockingStub(channel);

        frame = new JFrame("Restaurant Client");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton sendButton = new JButton("Send to Server");

        inputField.addActionListener(e -> sendCommand());

        sendButton.addActionListener(e -> sendCommand());

        panel.add(inputField, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);


        outputArea.append("Welcome to Restaurant x!\n");
        outputArea.append("Login: LOGIN <username> <password>\n\n");
    }

    private static void sendCommand() {
        String command = inputField.getText().trim();

        if (command.isEmpty()) return;

        CommandRequest request = CommandRequest.newBuilder()
                .setCommand(command)
                .setSessionId(sessionId == null ? "" : sessionId)
                .build();

        CommandResponse response = stub.sendCommand(request);

        sessionId = response.getSessionId();

        outputArea.append("> " + command + "\n");
        outputArea.append(response.getResponse() + "\n\n");

        if (command.equalsIgnoreCase("EXIT")) {
            frame.dispose();
        }

        inputField.setText("");
    }
}