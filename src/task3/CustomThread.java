package task3;

import java.util.concurrent.CountDownLatch;

public class CustomThread implements Runnable {
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;

    public CustomThread(CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            finishLatch.countDown();
        }
    }
}
