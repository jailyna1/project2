// package com.restaurant.client;

// import java.io.*;
// import java.net.Socket;
// import java.util.Scanner;

// public class RestaurantClient {
//     public static void main(String[] args) throws Exception {
//         String host = "localhost";
//         int port = 5000;
        
//         try (Socket s = new Socket(host, port);
//              BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//              BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//              Scanner sc = new Scanner(System.in)) {

//             System.out.println(in.readLine());
//             while (true) {
//                 System.out.print(">> ");
//                 String line = sc.nextLine();
//                 if (line == null) break;
//                 out.write(line);
//                 out.newLine();
//                 out.flush();

//                 String resp = in.readLine();
//                 System.out.println("ROLE: " + resp);
//                 if ("LOGOUT".equalsIgnoreCase(line.trim())) break;
//             }
//         }
//     }
// }
