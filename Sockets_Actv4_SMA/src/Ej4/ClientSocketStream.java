package Ej4;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocketStream {
    public static void main (String[ ] args) {
        try {
            System.out.println("Creating customer socket...");
            Socket clientSocket = new Socket();

            System.out.println("Making connection...");
            InetSocketAddress address = new InetSocketAddress("localhost", 5555);
            clientSocket.connect(address);

            InputStream inputStream = clientSocket.getInputStream();
            byte[] message = new byte[25];
            inputStream.read(message);
            System.out.println("Message from server: " + new String(message));

            System.out.println("Closing customer socket...");
            clientSocket.close();

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}