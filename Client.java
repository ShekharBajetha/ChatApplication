import java.net.Socket;
import java.io.*;

class Client {
    BufferedReader br;
    PrintWriter out;

    Socket socket;

    public Client() {
        try {
            System.out.println("Sending request to Server");
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("Connection done..");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
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
                        System.out.println("Sever terminated the Chat");
                        socket.close();
                        break;
                    }
                    System.out.println("Server : " + msg);

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
                        System.out.println("Server terminated the Chat");
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
        System.out.println("Client Started...");
        new Client();
    }
}
