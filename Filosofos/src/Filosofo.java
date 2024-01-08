import java.util.concurrent.locks.Lock;

class Filosofo implements Runnable {
    private int id;
    private Lock tenedorIzquierdo;
    private Lock tenedorDerecho;

    public Filosofo(int id, Lock tenedorIzquierdo, Lock tenedorDerecho) {
        this.id = id;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pasando.");
        Thread.sleep(3000);
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comiendo.");
        Thread.sleep(3000);
    }

    @Override
    public void run() {
        try {
            while(true) {
                pensar();

                tenedorIzquierdo.lock();
                tenedorDerecho.lock();

                comer();

                tenedorDerecho.unlock();
                tenedorIzquierdo.unlock();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
