package task4;


import java.util.concurrent.locks.ReentrantLock;

public class CustomLockThread implements Runnable {
    private final CommonResource commonResource;
    private final ReentrantLock lock;


    public CustomLockThread(CommonResource commonResource, ReentrantLock lock) {
        this.commonResource = commonResource;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 1_000_000; i++) {
                commonResource.increment();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}
