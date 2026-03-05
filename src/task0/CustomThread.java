package task0;

import java.util.concurrent.CountDownLatch;

public class CustomThread implements Runnable {
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;

    public CustomThread(CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.finishLatch = finishLatch;
        this.startLatch = startLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " еще не готов начать работу");
            startLatch.await();
            try {
                System.out.println(Thread.currentThread().getName() + " выполняет работу");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                finishLatch.countDown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
