// package com.restaurant.client;

// import java.io.*;
// import java.net.Socket;
// import java.util.Scanner;


// public class ProtocolDemo {
//     public static void main(String[] args) throws Exception {
        
//         new ProtocolDemo().run("localhost", 5000, "chef", "pass");
//     }

//     public void run(String host, int port, String user, String pass) throws Exception {
//         try (Socket socket = new Socket(host, port);
//              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//              BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//              Scanner sc = new Scanner(System.in)) {

//             System.out.println(in.readLine()); 

//             out.write("LOGIN " + user + " " + pass);
//             out.newLine();
//             out.flush();
//             System.out.println(in.readLine());

            
//             out.write("LIST_MENU");
//             out.newLine();
//             out.flush();
//             System.out.println(in.readLine());

//             out.write("PUT_PRICE Grilled Salmon 19.49");
//             out.newLine();
//             out.flush();
//             System.out.println(in.readLine());

//             out.write("LOGOUT");
//             out.newLine();
//             out.flush();
//             System.out.println(in.readLine());
//         }
//     }
// }
