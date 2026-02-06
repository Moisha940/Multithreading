package task0;

import java.util.concurrent.Semaphore;

public class CustomThread implements Runnable {
    private final Semaphore semaphore;

    public CustomThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting");
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName() + " is stopped");
            semaphore.release();
        }
    }
}
