package task1;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThread implements Runnable {
    private final static int NUMBER_OF_ITERATIONS = 5;

    private final CommonResource commonResource;
    private final ReentrantLock lock;

    public CustomThread(CommonResource commonResource, ReentrantLock lock) {
        this.commonResource = commonResource;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
                try {
                    for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                        commonResource.increment();
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    lock.unlock();
                }
            } else {
                // Here could be some alternative logic
                System.out.println(Thread.currentThread().getName() + ": Не удалось захватить lock за 10 мс");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
