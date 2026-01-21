package task0;

import java.util.concurrent.locks.ReentrantLock;

public class CustomThread implements Runnable {
    private final static int NUMBER_OF_ITERATIONS = 100;

    private final CommonResource commonResource;
    private final ReentrantLock lock;

    public CustomThread(CommonResource commonResource, ReentrantLock lock) {
        this.commonResource = commonResource;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
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
