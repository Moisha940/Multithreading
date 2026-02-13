package task2;

import java.util.concurrent.CyclicBarrier;

// 5.2
// Write a Java program to implement a cyclic barrier with a timeout
// and properly handle TimeoutException scenarios.
public class Main {

    private final static int NUMBER_OF_THREADS = 4;
    private final static int NUMBER_OF_AWAITS = 2;

    public static void main(String[] args) throws InterruptedException {
        CustomCyclicBarrier cyclicBarrier = new CustomCyclicBarrier(NUMBER_OF_AWAITS);
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
