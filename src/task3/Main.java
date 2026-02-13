package task3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

// 5.3
// Write a Java program to simulate iterative computations
// where threads wait at a CyclicBarrier after each iteration.
public class Main {
    private final static int NUMBER_OF_THREADS = 3;
    private final static int NUMBER_OF_AWAITS = 3;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_AWAITS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(cyclicBarrier, counter));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nDemonstration finished. Counter: 3 * 5 = " + counter.get());
    }
}