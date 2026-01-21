package task2;

import java.util.concurrent.locks.ReentrantLock;

public class CustomThread implements Runnable {
    private final static int NUMBER_OF_ITERATIONS = 10;

    private final CommonResource commonResource;
    private final ReentrantLock lock;

    public CustomThread(CommonResource commonResource, ReentrantLock lock) {
        this.commonResource = commonResource;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            System.out.println("Trying to lock: " + Thread.currentThread().getName());
            lock.lock();
            System.out.println("Locked by: " + Thread.currentThread().getName());
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                commonResource.increment();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}
