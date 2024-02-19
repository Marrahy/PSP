import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(55555);
            System.out.println("Servidor iniciado. Esperando cliente...");

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + socket.getInetAddress() + ":" + socket.getPort());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            Random random = new Random();
            int contadorTocado = 0;

            while (true) {
                // Espera la entrada del cliente
                String coordenadasCliente = entrada.readLine();
                System.out.println("El cliente envió: " + coordenadasCliente);

                // Genera respuesta basada en un número aleatorio
                int numeroAleatorio = random.nextInt(10) + 1;
                if (numeroAleatorio <= 5) {
                    salida.println("Tocado");
                    contadorTocado++;
                } else {
                    salida.println("Agua");
                }

                // Genera coordenadas para el cliente
                char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
                char letraAleatoria = letras[random.nextInt(letras.length)];
                int numeroAleatorio2 = random.nextInt(10) + 1;
                String coordenadasServidor = letraAleatoria + "" + numeroAleatorio2;
                salida.println(coordenadasServidor);

                // Verifica si el servidor ganó
                if (contadorTocado == 5) {
                    System.out.println("El servidor pierde! El cliente ha ganado.");
                    salida.println("El servidor pierde! El cliente ha ganado.");
                    break;
                }
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
