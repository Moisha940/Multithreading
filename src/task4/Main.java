package task4;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

// 1.4
// Write a Java program to create multiple threads that increment a shared counter and
// use a CyclicBarrier to synchronize their completion before printing the result.
public class Main {
    public static final int NUMBER_OF_THREADS = 5;
    public static final int NUMBER_OF_ITERATIONS = 70;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_THREADS, new FinalTaskForCyclicBarrier(atomicInteger));
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(atomicInteger, cyclicBarrier, NUMBER_OF_ITERATIONS));
            Thread.sleep(10);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
