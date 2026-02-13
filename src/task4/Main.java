package task4;

import java.util.concurrent.CyclicBarrier;

// 5.4
// Write a Java program to use CyclicBarrier to coordinate phases of a task
// and reset the barrier for repeated use.
public class Main {
    private final static int NUMBER_OF_THREADS = 3;
    private final static int NUMBER_OF_AWAITS = 3;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_AWAITS, () -> {
            System.out.println("Phase was completed. CyclicBarrier will be reset\n");
        });

        System.out.println("=== Demonstration started ===");
        startThread(cyclicBarrier);
        cyclicBarrier.reset();

        startThread(cyclicBarrier);
        cyclicBarrier.reset();

        System.out.println("=== Demonstration finished ===");
    }


    private static void startThread(CyclicBarrier cyclicBarrier) throws InterruptedException {
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(cyclicBarrier));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}