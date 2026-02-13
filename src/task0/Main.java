package task0;

import java.util.concurrent.CyclicBarrier;

// 5.0
// Write a Java program to showcase the usage of the CyclicBarrier class for thread synchronization.
public class Main {
    private final static int NUMBER_OF_THREADS = 4;
    private final static int NUMBER_OF_AWAITS = 2;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_AWAITS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(cyclicBarrier));
            Thread.sleep(800);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nDemonstration finished");
    }
}