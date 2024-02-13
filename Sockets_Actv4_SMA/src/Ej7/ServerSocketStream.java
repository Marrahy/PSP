package Ej7;

import java.io.IOException;
import java.net.*;

public class ServerSocketStream {
    public static void main(String[] args) {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost" , 5555);
            DatagramSocket datagramSocket = new DatagramSocket(address);

            System.out.println("Getting message...");
            byte[] message = new byte[5];
            DatagramPacket serverDatagram = new DatagramPacket(message, 5);
            datagramSocket.receive(serverDatagram);
            System.out.println("Client message: " + new String(message));
            String clientDatagramToString = new String(message);

            if (clientDatagramToString.equals("token")) {
                String serverMessage = "Recibido";
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
