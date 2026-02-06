package task0;

import java.util.concurrent.Semaphore;

// 4.0
// Write a Java program to demonstrate Semaphore usage for thread synchronization.
public class Main {
    private static final int NUMBER_OF_THREADS = 5;
    private static final int NUMBER_OF_PERMITS = 2;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(NUMBER_OF_PERMITS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(semaphore));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nDemonstration completed.");
    }
}