import java.io.*;
import java.net.*;

public class ClientSide {
    public static void main(String[] args) {
        try {
            Socket clienteSocket = new Socket("localhost", 5000);
            System.out.println("Conectado al servidor.");

            // Inicializar I/O streams
            BufferedReader entradaDesdeServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salidaAServidor = new PrintWriter(clienteSocket.getOutputStream(), true);

            BufferedReader entradaDesdeUsuario = new BufferedReader(new InputStreamReader(System.in));

            // Lógica del juego
            int contadorTocado = 0;
            while (contadorTocado < 5) {
                // Ingresar coordenadas para enviar al servidor
                System.out.print("Ingrese coordenadas (por ejemplo, B6): ");
                String coordenadasUsuario = entradaDesdeUsuario.readLine();
                salidaAServidor.println(coordenadasUsuario);

                // Recibir respuesta del servidor
                String respuestaServidor = entradaDesdeServidor.readLine();
                System.out.println("Respuesta del servidor: " + respuestaServidor);

                // Verificar si el cliente ha ganado
                if (respuestaServidor.equals("Tocado")) {
                    contadorTocado++;
                    System.out.println("Tocado " + contadorTocado + " veces.");
                    if (contadorTocado == 5) {
                        break;
                    }
                }

                // Recibir coordenadas del servidor
                String coordenadasServidor = entradaDesdeServidor.readLine();
                System.out.println("Coordenadas del servidor: " + coordenadasServidor);

                String respuestaCliente = entradaDesdeUsuario.readLine();
                System.out.println("Respuesta del cliente: " + respuestaCliente);
                salidaAServidor.println(respuestaCliente);
            }

            System.out.println("Has ganado! Juego terminado.");

            // Cerrar conexiones
            clienteSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
