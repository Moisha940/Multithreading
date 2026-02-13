package task1;

import java.util.concurrent.CyclicBarrier;

// 5.1
// Write a Java program to synchronize multiple threads at a barrier using CyclicBarrier
// and execute a collective action when all arrive.
public class Main {
    private final static int NUMBER_OF_THREADS = 4;
    private final static int NUMBER_OF_AWAITS = 2;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_AWAITS,
                () -> {
                    System.out.println("~~~~~~~~ " + Thread.currentThread().getName() + " последним преодолел барьер ~~~~~~~~");
                });

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