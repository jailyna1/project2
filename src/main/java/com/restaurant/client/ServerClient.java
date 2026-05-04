// package com.restaurant.client;

// import java.io.*;
// import java.net.Socket;
// import java.util.Scanner;

// public class ServerClient {
//     public static void main(String[] args) throws Exception {
//         runClient("localhost", 5000, "server", "pass");
//     }

//     public static void runClient(String host, int port, String user, String pass) throws Exception {
//         try (Socket socket = new Socket(host, port);
//              BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//              BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//              Scanner userInput = new Scanner(System.in)) {

//             String welcome;
//             while ((welcome = serverIn.readLine()) != null && welcome.isEmpty()) {}
//             System.out.println(welcome);

//             System.out.println("\nServer Command List: ");
//             System.out.println("LOGIN <user> <pass>");
//             System.out.println("LIST_MENU");
//             System.out.println("LIST_ORDERS");
//             System.out.println("PLACE_TAKEOUT_ORDER <customer_name> <item1> <item2> ... (up to 10 items)");
//             System.out.println("PLACE_DINEIN_ORDER | <customer_name1> <item1> <item2> ... | <customer_name2> <item1> <item2> ... | ... (up to 4 items per guest, up to 4 guests)");
//             System.out.println("SHOW_BILL <order_id>");
//             System.out.println("AVAILABLE_COMMANDS");
//             System.out.println("LOGOUT");
//             System.out.println("EXIT");
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
