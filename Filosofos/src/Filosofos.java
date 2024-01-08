import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofos {
    public static void main(String[] args) {
        int numFilosofos = 5;
        Lock[] tenedores = new Lock[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            tenedores[i] = new ReentrantLock();
        }

        Thread[] filosofos = new Thread[numFilosofos];
        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Thread(new Filosofo(i, tenedores[i], tenedores[(i + 1) % numFilosofos]));
            filosofos[i].start();
        }
    }
}
