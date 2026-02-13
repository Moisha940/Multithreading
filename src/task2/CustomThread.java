package task2;

import java.util.concurrent.BrokenBarrierException;

public class CustomThread implements Runnable {
    private final CustomCyclicBarrier cyclicBarrier;

    public CustomThread(CustomCyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " начал работу и ждет у барьера");

            Thread.sleep(1000);

            cyclicBarrier.await();
            System.out.println("Барьер был преодолен " + Thread.currentThread().getName());
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
