package Ej5;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

public class ServerSocketStream {
    private static int clientCount = 0;
    private static ServerSocket serverSocket;
    private static Timer serverTimer;

    public static void main (String[] args) {

        try {
            serverSocket = new ServerSocket();
            InetSocketAddress address = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(address);

            serverTimer = new Timer();
            serverTimer.schedule(new ServerShutdownTask(), 10000);

            System.out.println("Server started. Listening for connections...\n");

            while(true) {
                Socket newSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + newSocket.getInetAddress() + "\n");

                resetServerTimer();

                ClientHandler newClient = new ClientHandler(newSocket);
                Thread clientThread = new Thread(newClient);
                clientThread.start();

                clientCount++;
                System.out.println("Clients in total: " + clientCount + "\n");
            }
        } catch (IOException e) {

        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = clientSocket.getOutputStream();
                String serverMessage = "Hi client " + clientCount;
                outputStream.write(serverMessage.getBytes());

                Thread.sleep(10000);

                clientSocket.close();
                clientCount--;
                System.out.println("Client disconnected. Clients remaining " + clientCount);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class ServerShutdownTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("No activity for 10 seconds. Shutting down server...");

            try {
                serverSocket.close();
                serverTimer.cancel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void resetServerTimer() {
        serverTimer.cancel();
        serverTimer = new Timer();
        serverTimer.schedule(new ServerShutdownTask(), 10000);
    }
}
