package task2;


import java.util.concurrent.CountDownLatch;

// 6.2
// Write a Java program to implement a pre-processing phase using CountDownLatch
// before executing main tasks concurrently.
public class Main {
    private static final int NUM_THREADS = 10;
    private static final CountDownLatch startLatch = new CountDownLatch(NUM_THREADS);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new CustomThread(startLatch));
            threads[i].start();
            Thread.sleep(100);
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }
    }
}