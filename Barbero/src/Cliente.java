class Cliente implements Runnable {
    private Barberia barberia;

    public Cliente(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        try {
            barberia.cortarPelo();
            System.out.println("El barbero ha terminado con el corte del pelo del cliente.");
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
