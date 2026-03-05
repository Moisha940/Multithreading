package task2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CustomThread implements Runnable {
    private final CountDownLatch startLatch;

    public CustomThread(CountDownLatch startLatch) {
        this.startLatch = startLatch;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            System.out.println(Thread.currentThread().getName() + " осуществляет пред-подготовка ----");
            Thread.sleep((4 + random.nextInt(5)) * 1000L);
            startLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " завершил пред-подготовку и готов к работе ++++");
            startLatch.await();

            System.out.println(Thread.currentThread().getName() + " выполняет работу");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
