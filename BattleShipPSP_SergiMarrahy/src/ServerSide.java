import java.io.*;
import java.net.*;

public class ServerSide {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor en linea. Esperando conexiones...");

            Socket clienteSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Inicializar I/O streams
            BufferedReader entradaDesdeCliente = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salidaACliente = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Lógica del juego
            int contadorTocado = 0;
            boolean primerTurno = true; // Indica si es el primer turno del juego

            while (contadorTocado < 5) {
                if (!primerTurno) {
                    // Simular respuesta del servidor (agua o tocado)
                    String coordenadasServidor = generarCoordenadas();
                    System.out.println("Coordenadas del servidor: " + coordenadasServidor);

                    int respuesta = (int) (Math.random() * 10) + 1;
                    if (respuesta <= 5) {
                        salidaACliente.println("Agua");
                    } else {
                        salidaACliente.println("Tocado");
                        contadorTocado++;
                    }
                } else {
                    primerTurno = false;
                }

                // Recibir coordenadas del cliente
                String coordenadasCliente = entradaDesdeCliente.readLine();
                System.out.println("Coordenadas recibidas del cliente: " + coordenadasCliente);

                // Verificar si el servidor ha ganado
                if (contadorTocado == 5) {
                    System.out.println("El servidor ha ganado! Juego terminado.");
                    break;
                }
            }

            // Cerrar conexiones
            clienteSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para generar coordenadas del servidor (simulación)
    private static String generarCoordenadas() {
        char[] columnas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int fila = (int) (Math.random() * 10) + 1;
        return columnas[(int)(Math.random() * 10)] + String.valueOf(fila);
    }
}
