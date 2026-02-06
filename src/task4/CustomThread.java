package task4;

import java.util.concurrent.Semaphore;

public class CustomThread implements Runnable {
    private final Semaphore semaphore;

    public CustomThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("+++ " + Thread.currentThread().getName() + " before acquiring.");
            semaphore.acquire();
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " is processing.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            System.out.println("--- " + Thread.currentThread().getName() + " was released.");
        }
    }
}
