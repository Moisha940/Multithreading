package task4;

import java.util.concurrent.atomic.AtomicInteger;

public class FinalTaskForCyclicBarrier implements Runnable {
    private final AtomicInteger atomicInteger;

    public FinalTaskForCyclicBarrier(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        System.out.println("All threads finished incrementing and result is " + atomicInteger.get());
    }
}
