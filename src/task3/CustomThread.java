package task3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThread implements Runnable {
    private final CyclicBarrier cyclicBarrier;
    private final AtomicInteger atomicInteger;

    public CustomThread(CyclicBarrier cyclicBarrier, AtomicInteger atomicInteger) {
        this.cyclicBarrier = cyclicBarrier;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                atomicInteger.incrementAndGet();
                cyclicBarrier.await();
                System.out.println("Все потоки прошли барьер. Итерация № " + i);
            }

            Thread.sleep(1000);

        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
