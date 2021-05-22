import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

import java.io.*;

public class Server {

    ServerSocket serverSocket;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    public Server() {
        try {

            serverSocket = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting...");
            socket = serverSocket.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reading....");
            try {
                while (!socket.isClosed()) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the Chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Client : " + msg);
                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Connection is Closed");

            }
        };
        new Thread(r1).start();
    }

    private void startWriting() {
        Runnable r2 = () -> {
            System.out.println("Writer Started...");
            try {
                while (!socket.isClosed()) {
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                    if (content.equals("exit")) {
                        System.out.println("Client terminated the Chat");
                        socket.close();
                        break;
                    }
                }
                System.out.println("Connection is Closed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("Server Started");
        new Server();
    }

}
