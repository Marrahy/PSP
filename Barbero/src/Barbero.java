class Barbero implements Runnable {
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            barberia.esperarCliente();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
