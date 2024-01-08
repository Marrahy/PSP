public class BarberoDormilon {
    public static void main(String[] args) {
        Barberia barberia = new Barberia();

        Thread barberoThread = new Thread(new Barbero(barberia));
        barberoThread.start();

        for (int i = 0; i < 5; i++) {
            Thread clienteThread = new Thread(new Cliente(barberia));
            clienteThread.start();
        }
    }
}
