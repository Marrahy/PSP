import java.util.concurrent.Semaphore;

class Barberia {
    private Semaphore silla = new Semaphore(1);
    private Semaphore barbero = new Semaphore(0);
    private Semaphore cliente = new Semaphore(0);

    public void cortarPelo() throws InterruptedException {
        silla.acquire();
        barbero.release();
        cliente.acquire();
        silla.release();
    }

    public void esperarCliente() throws InterruptedException {
        while(true) {
            barbero.acquire();
            System.out.println("Barbero cortando pelo.");
            Thread.sleep(3000);
            cliente.release();
        }
    }
}
