package Ej4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerSocketStream {
    public static void main (String[ ] args) {
        Path path = Paths.get("src/Ej4/Ej4.txt");

        try {
            System.out.println("Creating server socket");
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Binding...");
            InetSocketAddress address = new InetSocketAddress("localhost" , 5555) ;
            serverSocket.bind(address);

            System.out.println("Getting connections...");
            Socket newSocket = serverSocket.accept();

            System.out.println("Connection established!");
            OutputStream outputStream = newSocket.getOutputStream();
            System.out.println("Sending text message...");
            byte[] message = Files.readAllBytes(path);
            outputStream.write(message);

            System.out.println("Closing socket...");
            newSocket.close();

            System.out.println("Closing server socket...");
            serverSocket.close();

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
