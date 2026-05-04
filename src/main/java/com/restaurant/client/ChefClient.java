// package com.restaurant.client;

// import java.io.*;
// import java.net.Socket;
// import java.util.Scanner;

// public class ChefClient {
//     public static void main(String[] args) throws Exception {
//         runClient("localhost", 5000, "chef", "pass");
//     }
    
//     public static void runClient(String host, int port, String user, String pass) throws Exception {
//         try (Socket socket = new Socket(host, port);
//              BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//              BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//              Scanner userInput = new Scanner(System.in)) {

//             String welcome;
//             while ((welcome = serverIn.readLine()) != null && welcome.isEmpty()) {}
//             System.out.println(welcome);

//             System.out.println("\nChef Command List: ");
//             System.out.println("LOGIN <user> <pass>");
//             System.out.println("LIST_ORDERS");
//             System.out.println("NOTIFY_ORDER_READY <id>");
//             System.out.println("AVAILABLE_COMMANDS");
//             System.out.println("LOGOUT");
//             System.out.println("--------------------------\n");

//             while (true) {
//                 System.out.print("> ");
//                 if (!userInput.hasNextLine()) break;
                
//                 String command = userInput.nextLine().trim();
//                 if (command.isEmpty()) continue;
                
//                 serverOut.write(command + "\n");
//                 serverOut.flush();
                
//                 String response = serverIn.readLine();
//                 if (response != null) {
//                     System.out.println(response);
//                 }
//             }
//         }
//     }
// }
