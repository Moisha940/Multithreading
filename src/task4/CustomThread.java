package task4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThread implements Runnable {
    private final AtomicInteger counter;
    private final CyclicBarrier cyclicBarrier;
    private final int NUMBER_OF_ITERATIONS;

    public CustomThread(AtomicInteger counter, CyclicBarrier cyclicBarrier, int NUMBER_OF_ITERATIONS) {
        this.counter = counter;
        this.cyclicBarrier = cyclicBarrier;
        this.NUMBER_OF_ITERATIONS = NUMBER_OF_ITERATIONS;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
                counter.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + "overcame the barrier. Counter: " + counter);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
