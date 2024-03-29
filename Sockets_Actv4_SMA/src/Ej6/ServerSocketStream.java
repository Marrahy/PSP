package Ej6;

import java.io.IOException;
import java.net.*;

public class ServerSocketStream {
    public static void main(String[] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost" , 5555);
            DatagramSocket datagramSocket = new DatagramSocket(address);

            System.out.println("Getting message...");
            byte[] message = new byte[4];
            DatagramPacket serverDatagram = new DatagramPacket(message, 4);
            datagramSocket.receive(serverDatagram);
            System.out.println("Message: " + new String(message));
            String clientDatagramToString = new String(message);

            if (clientDatagramToString.equals("Hola")) {
                String serverMessage = "¿Qué tal?";
                System.out.println("Sending message...");
                byte[] response = serverMessage.getBytes();
                InetAddress secondAddress = serverDatagram.getAddress();
                DatagramPacket secondDatagram = new DatagramPacket(response, response.length, secondAddress, 5556);
                datagramSocket.send(secondDatagram);
                System.out.println("Message sent");
            }

            System.out.println("Closing datagram socket");
            datagramSocket.close();

            System.out.println("Done");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
