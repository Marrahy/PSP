package Ej5;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocketStream {
    public static void main (String[ ] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 5555);

            for (int i = 0; i < 10; i++) {
                Socket clientSocket = new Socket();
                System.out.println("Creating customer socket...\n");

                System.out.println("Making connection...\n");
                clientSocket.connect(address);

                InputStream inputStream = clientSocket.getInputStream();
                byte[] message = new byte[11];
                inputStream.read(message);

                System.out.println("Message from server: " + new String(message));
                System.out.println("10 seconds before closing the connection.\n");

                Thread.sleep(1000);

                System.out.println("Closing customer socket...\n");
                clientSocket.close();
            }

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}