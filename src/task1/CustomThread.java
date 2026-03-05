package task1;

import java.util.concurrent.CountDownLatch;

public class CustomThread implements Runnable {
    private final CountDownLatch startLatch;

    public CustomThread(CountDownLatch startLatch) {
        this.startLatch = startLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " ждут разрешения начать работу");
            startLatch.await();

            System.out.println(Thread.currentThread().getName() + " выполняет работу");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
