package task3;

import java.util.concurrent.Semaphore;

public class ResourcePool {
    private final Semaphore semaphore;

    public ResourcePool(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public void connect() {
        try {
            System.out.println("+++ Waiting for resource pool to connect... " + Thread.currentThread().getName());
            semaphore.acquire();
            System.out.println("Connected... " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            new RuntimeException(e);
        } finally {
            semaphore.release();
            System.out.println("---Resource pool disconnecting..." + Thread.currentThread().getName());
        }
    }

}
