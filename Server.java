import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Server extends JFrame {

    ServerSocket serverSocket;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    // Declare Components
    private JLabel heading = new JLabel("Server Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    public Server() {
        try {

            serverSocket = new ServerSocket(7778);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting...");
            socket = serverSocket.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            createGUI();
            startReading();
            handleEvents();
            // startWriting();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleEvents() {

        messageInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                System.out.println("Key Released " + e.getKeyCode());
                if (e.getKeyCode() == 10) {
                    String contentToSend = messageInput.getText();
                    messageArea.append("Me: " + contentToSend);
                    out.println(contentToSend);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }
        });

    }

    private void createGUI() {

        // GUI
        this.setTitle("Server Messanger[END]");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);

        // Components
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        // frame Layout
        this.setLayout(new BorderLayout());
        this.add(heading, BorderLayout.NORTH);
        this.add(messageArea, BorderLayout.CENTER);
        this.add(messageInput, BorderLayout.SOUTH);

    }

    private void startReading() {
        Runnable r1 = () -> {
            System.out.println("Reading....");
            try {
                while (!socket.isClosed()) {
                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the Chat");
                        JOptionPane.showMessageDialog(this, "Client terminated the Chat ");
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

    /*
     * private void startWriting() { Runnable r2 = () -> {
     * System.out.println("Writer Started..."); try { while (!socket.isClosed()) {
     * BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
     * String content = br1.readLine(); out.println(content); out.flush(); if
     * (content.equals("exit")) { System.out.println("Client terminated the Chat");
     * socket.close(); break; } } System.out.println("Connection is Closed"); }
     * catch (Exception e) { e.printStackTrace(); } }; new Thread(r2).start(); }
     */
    public static void main(String[] args) {
        System.out.println("Server Started");
        new Server();
    }

}
