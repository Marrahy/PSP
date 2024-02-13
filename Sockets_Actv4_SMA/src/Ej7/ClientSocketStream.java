package Ej7;

import java.net.*;

public class ClientSocketStream {
    public static void main(String[] args) {
        try {
            System.out.println("Creating datagram socket");
            DatagramSocket datagramSocket = new DatagramSocket();

            System.out.println("Sending message...");
            String message = "token";

            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket datagram = new DatagramPacket(message.getBytes(), message.getBytes().length, address, 5555);
            datagramSocket.send(datagram);
            System.out.println("Message sent");

            InetSocketAddress secondAddress = new InetSocketAddress("localhost" , 5556);
            DatagramSocket secondDatagramSocket = new DatagramSocket(secondAddress);

            byte[] serverMessage = new byte[11];
            DatagramPacket clientDatagram = new DatagramPacket(serverMessage, 11);
            secondDatagramSocket.receive(clientDatagram);
            String serverResponse = new String(clientDatagram.getData(), 0, clientDatagram.getLength());
            System.out.println("Server response: " + serverResponse);

            System.out.println("Closing datagram socket");
            datagramSocket.close();

            System.out.println("Done");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}