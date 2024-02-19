import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 55555);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            int contadorTocado = 0;

            while (true) {
                // Solicita al cliente que ingrese las coordenadas
                String clientCoordinates = getCoordinates(teclado);
                salida.println(clientCoordinates);

                // Espera la respuesta del servidor
                String respuestaServidor = entrada.readLine();

                // Recibe las coordenadas del servidor
                String coordenadasServidor = entrada.readLine();

                System.out.println("Respuesta del servidor: " + respuestaServidor);
                System.out.println("El servidor" +
                        " envió: " + coordenadasServidor);

                System.out.print("Introduce Agua o Tocado: ");
                String clientTouchedInput = teclado.readLine();
                if (clientTouchedInput.equalsIgnoreCase("Tocado")) {
                    contadorTocado++;
                    salida.println("Tocado!");
                    if (contadorTocado == 5) {
                        System.out.println("El cliente pierde! El servidor ha ganado.");
                        salida.println("El cliente pierde! El servidor ha ganado.");
                        break;
                    }
                }
                salida.println(clientTouchedInput);

                if (respuestaServidor == null || coordenadasServidor == null) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCoordinates(BufferedReader teclado) throws IOException {
        System.out.print("Introduce coordenadas (A-J/1-10): ");
        return teclado.readLine();
    }
}
